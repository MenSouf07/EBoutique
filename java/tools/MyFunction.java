package tools;

import java.sql.*;

public class MyFunction {
	
	private Connection myConnection;
    private PreparedStatement ps;
    private ResultSet rs;
	
    public MyFunction() {
    	myConnection = DBConnection.getConnection();
    	
    }
	
    public void updateProduct(Product product, int categoryId) {
        try {
            ps = myConnection.prepareStatement("UPDATE product SET title = ?, description = ?, price = ?, stock = ?, idCategory = ? WHERE idProduct = ?");
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getStock());
            ps.setInt(5, categoryId);
            ps.setInt(6, product.getIdProduct());
            ps.executeUpdate();
            ps.close();
       } catch (SQLException ex) {
           Logger.getLogger(CtrlAccount.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<>();
        try {
            ps = myConnection.prepareStatement("SELECT idProduct, title, description, price, stock, imagePath, category.name FROM product JOIN category ON product.idCategory = category.idCategory");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Product p = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7));
                allProducts.add(p);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CtrlProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allProducts;
    }
    
}
