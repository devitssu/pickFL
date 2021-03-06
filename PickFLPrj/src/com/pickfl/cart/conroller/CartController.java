package com.pickfl.cart.conroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pickfl.cart.model.service.CartService;
import com.pickfl.cart.model.vo.CartVo;
import com.pickfl.member.model.vo.MemberVo;
import com.pickfl.order.model.service.OrderService;
import com.pickfl.order.model.vo.OrderVo;



@WebServlet("/cart")
public class CartController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo currentUser = (MemberVo) req.getSession().getAttribute("loginUser");
		
		if (currentUser != null) {
//			장바구니에 보여질 값들 불러오기
			int currentUserMemNo = currentUser.getMemberNo();
			
			List<CartVo> cartList = new CartService().selectCartList(currentUserMemNo);
			
			int totalCartPrice = 0;
			int totalPNum = 0;
			String totalCartPriceST = "0";
			String totalPNumST = "0";
			
			for (CartVo cartVo : cartList) {
				totalCartPrice += cartVo.getBouquetTotalPrice();
			}

			for (CartVo cartVo : cartList) {
				totalPNum += cartVo.getBuyNumBQ();
			}
			
			int point = currentUser.getPoint();
			
			CartVo cartVo = new CartVo();
		
			System.out.println(totalPNumST);
			System.out.println(totalCartPriceST);
			
			req.setAttribute("cartVo", cartVo);
			int totalPrice = cartVo.getTotalCartPrice();
			
			req.setAttribute("totalPrice", totalPrice);
			req.setAttribute("point", point);
			
			cartVo.setTotalCartPrice(totalCartPrice);
			cartVo.setTotalPNum(totalPNum);
			
			req.setAttribute("totalPNum", totalPNum);
			req.getSession().setAttribute("totalCartPrice", totalCartPrice);

			req.setAttribute("cart", cartList);
			req.getRequestDispatcher("/WEB-INF/views/cart/cart.jsp").forward(req, resp);
		} else {
			req.setAttribute("msg", "로그인 먼저 해주세요.");
			
			req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		}
		
	}
	
}
