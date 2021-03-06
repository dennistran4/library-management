package com.librarymanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.librarymanagement.products.Product;
import com.librarymanagement.products.ProductManagement;
import com.librarymanagement.products.ProductManagementImpl;

public class ProductManagementSerializeble implements ProductManagement {
	public static int x = 0;
	/**
	 * written to file
	 */
	ProductManagement productManagement = new ProductManagementImpl();
	private static final long serialVersionUID = -623012340337380129L;

	public ProductManagementSerializeble() {
		ProductManagement product = read();
		if (product != null) {
			productManagement = product;
		}
	}
//Save the object in the file Product_Library.txt
	private void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("Product_Library.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(productManagement);
			out.close();
			fileOut.close();
		} catch (Exception e) {
		}
	}
//Read the file Product_Library.txt
	static ProductManagement read() {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		ProductManagement pM = null;

		try {
			fileIn = new FileInputStream("Product_Library.txt");
			in = new ObjectInputStream(fileIn);
			pM = (ProductManagement) in.readObject();
			in.close();
			fileIn.close();
			if (x == 0) {
				System.out.println("Welcome!");
				System.out.println("Succesfully initialized system state from file(s).");
				System.out.println();
				x = 1;
			}

		} catch (Exception e) {

		}
		return pM;

	}

	@Override
	public void insert(Product product) {
		productManagement.insert(product);
		save();
	}

	@Override
	public void deleteProduct(int productId) {
		productManagement.deleteProduct(productId);
		save();

	}

	@Override
	public void checkOut(int productId, String name, String phoneNumber) {
		productManagement.checkOut(productId, name, phoneNumber);
		save();
	}

	@Override
	public void checkIn(int productId) {
		productManagement.checkIn(productId);
		save();
	}

	@Override
	public Product info(int productId) {
		return productManagement.info(productId);
	}

	@Override
	public ArrayList<Product> list() {
		return productManagement.list();
	}

	@Override
	public Product findByIdOrThrow(int productId) throws IllegalArgumentException {
		return productManagement.findByIdOrThrow(productId);
	}

}
