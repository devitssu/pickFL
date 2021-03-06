package com.pickfl.products.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.pickfl.common.JDBCTemplate.*;
import com.pickfl.products.model.vo.ProductVo;

public class ProductDao {

	public int addProduct(Connection conn, ProductVo p) throws SQLException {
		
		String sql = "INSERT INTO PRODUCT(PRODUCT_NO, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_STOCK, FLOWER_LANG, PRODUCT_SIMPLE, PRODUCT_DETAIL, PRODUCT_COLOR, PRODUCT_SIZE, PRODUCT_IMAGE, REGISTER_TIME)"
				+ " VALUES(SEQ_PRODUCT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, p.getProductName());
		pstmt.setInt(2, p.getProductPrice());
		pstmt.setInt(3, p.getProductStock());
		pstmt.setString(4, p.getFlowerLang());
		pstmt.setString(5, p.getProductSimple());
		pstmt.setString(6, p.getProductDetail());
		pstmt.setString(7, p.getProductColor());
		pstmt.setString(8, p.getProductSize());
		pstmt.setString(9, p.getProductImage());
		
		result = pstmt.executeUpdate();
		} finally {			
			close(pstmt);
		}
		
		return result;
	}

	public int selectByName(Connection conn, String product_name) throws SQLException {
		
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_NAME = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_name);
			rs = pstmt.executeQuery();
			
			rs.next();
			result = rs.getInt(1);
			
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return result;
	}

	public List<ProductVo> selectProductAll(Connection conn) throws SQLException {
		
		String sql = "SELECT * FROM PRODUCT";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = new ArrayList<ProductVo>();
		try {			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNo = rs.getInt("PRODUCT_NO");
				String productName = rs.getString("PRODUCT_NAME");
				int productPrice = rs.getInt("PRODUCT_PRICE");
				int productStock = rs.getInt("PRODUCT_STOCK");
				String flower_lang = rs.getString("FLOWER_LANG");
				String simple = rs.getString("PRODUCT_SIMPLE");
				String detail = rs.getString("PRODUCT_DETAIL");
				String color = rs.getString("PRODUCT_COLOR");
				String size = rs.getString("PRODUCT_SIZE");
				String image = rs.getString("PRODUCT_IMAGE");
				Timestamp time =rs.getTimestamp("REGISTER_TIME");
				
				list.add(new ProductVo(productNo, productName, productPrice, productStock, flower_lang, simple, detail, color, size, image, time));
			}
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	public ProductVo selectByNo(Connection conn, int product_no) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NO = ?";
		ProductVo p = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNo = rs.getInt("PRODUCT_NO");
				String productName = rs.getString("PRODUCT_NAME");
				int productPrice = rs.getInt("PRODUCT_PRICE");
				int productStock = rs.getInt("PRODUCT_STOCK");
				String flower_lang = rs.getString("FLOWER_LANG");
				String simple = rs.getString("PRODUCT_SIMPLE");
				String detail = rs.getString("PRODUCT_DETAIL");
				String color = rs.getString("PRODUCT_COLOR");
				String size = rs.getString("PRODUCT_SIZE");
				String image = rs.getString("PRODUCT_IMAGE");
				Timestamp time =rs.getTimestamp("REGISTER_TIME");
				
				p = new ProductVo(productNo, productName, productPrice, productStock, flower_lang, simple, detail, color, size, image, time);
			}
			
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return p;
	}

	public int deleteProduct(Connection conn, int product_no) throws SQLException {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM PRODUCT WHERE PRODUCT_NO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_no);
			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<ProductVo> searchByName(Connection conn, String searchName) throws SQLException {
		
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_NAME LIKE ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = new ArrayList<ProductVo>();
		try {			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchName+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNo = rs.getInt("PRODUCT_NO");
				String productName = rs.getString("PRODUCT_NAME");
				int productPrice = rs.getInt("PRODUCT_PRICE");
				int productStock = rs.getInt("PRODUCT_STOCK");
				String flower_lang = rs.getString("FLOWER_LANG");
				String simple = rs.getString("PRODUCT_SIMPLE");
				String detail = rs.getString("PRODUCT_DETAIL");
				String color = rs.getString("PRODUCT_COLOR");
				String size = rs.getString("PRODUCT_SIZE");
				String image = rs.getString("PRODUCT_IMAGE");
				Timestamp time =rs.getTimestamp("REGISTER_TIME");
				
				list.add(new ProductVo(productNo, productName, productPrice, productStock, flower_lang, simple, detail, color, size, image, time));
			}
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	public List<ProductVo> selectByColor(Connection conn, String product_color) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_COLOR = ?";
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product_color);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int productNo = rs.getInt("PRODUCT_NO");
				String productName = rs.getString("PRODUCT_NAME");
				int productPrice = rs.getInt("PRODUCT_PRICE");
				int productStock = rs.getInt("PRODUCT_STOCK");
				String flower_lang = rs.getString("FLOWER_LANG");
				String simple = rs.getString("PRODUCT_SIMPLE");
				String detail = rs.getString("PRODUCT_DETAIL");
				String color = rs.getString("PRODUCT_COLOR");
				String size = rs.getString("PRODUCT_SIZE");
				String image = rs.getString("PRODUCT_IMAGE");
				Timestamp time =rs.getTimestamp("REGISTER_TIME");
				
				list.add(new ProductVo(productNo, productName, productPrice, productStock, flower_lang, simple, detail, color, size, image, time));
			}
			
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return list;
	}

	public int updateProduct(Connection conn, ProductVo p) throws SQLException {
		String sql = "";
		PreparedStatement pstmt = null;
		int result = 0;
		
		if(p.getProductImage().equals("")) {
			sql = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ?, PRODUCT_STOCK = ?, FLOWER_LANG = ?, PRODUCT_SIMPLE = ?, PRODUCT_DETAIL = ?, PRODUCT_COLOR = ?, PRODUCT_SIZE = ? WHERE PRODUCT_NO = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getProductName());
				pstmt.setInt(2, p.getProductPrice());
				pstmt.setInt(3, p.getProductStock());
				pstmt.setString(4, p.getFlowerLang());
				pstmt.setString(5, p.getProductSimple());
				pstmt.setString(6, p.getProductDetail());
				pstmt.setString(7, p.getProductColor());
				pstmt.setString(8, p.getProductSize());
				pstmt.setInt(9, p.getProductNo());
				
				result = pstmt.executeUpdate();
			} finally {			
				close(pstmt);
			}
		}else {			
			sql = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ?, PRODUCT_STOCK = ?, FLOWER_LANG = ?, PRODUCT_SIMPLE = ?, PRODUCT_DETAIL = ?, PRODUCT_COLOR = ?, PRODUCT_SIZE = ?, PRODUCT_IMAGE = ? WHERE PRODUCT_NO = ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, p.getProductName());
				pstmt.setInt(2, p.getProductPrice());
				pstmt.setInt(3, p.getProductStock());
				pstmt.setString(4, p.getFlowerLang());
				pstmt.setString(5, p.getProductSimple());
				pstmt.setString(6, p.getProductDetail());
				pstmt.setString(7, p.getProductColor());
				pstmt.setString(8, p.getProductSize());
				pstmt.setString(9, p.getProductImage());
				pstmt.setInt(10, p.getProductNo());
				
				result = pstmt.executeUpdate();
			} finally {			
				close(pstmt);
			}
		}
		
		return result;
	}

	public List<ProductVo> selectNewest(Connection conn) throws SQLException {
		String sql = "SELECT * FROM ( SELECT * FROM PRODUCT ORDER BY REGISTER_TIME DESC ) WHERE ROWNUM <= 8";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductVo> list = new ArrayList<ProductVo>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				int productNo = rs.getInt("PRODUCT_NO");
				String productName = rs.getString("PRODUCT_NAME");
				String flower_lang = rs.getString("FLOWER_LANG");
				String simple = rs.getString("PRODUCT_SIMPLE");
				String image = rs.getString("PRODUCT_IMAGE");
				
				list.add(new ProductVo(productNo, productName, flower_lang, simple, image));
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

}
