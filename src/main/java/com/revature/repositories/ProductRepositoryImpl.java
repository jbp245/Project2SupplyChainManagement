package com.revature.repositories;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Product;

public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	SessionFactory sf;

	@Override
	public void addProduct(Product product) {

		Session sess = sf.openSession();
		Transaction tx = null;

		try {
			tx = sess.beginTransaction();
			sess.persist(product);
			tx.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
	}

	@Override
	public Product getProduct(int id) {
		Session sess = sf.openSession();
		Product product = null;
		
		try {
			product = (Product) sess.get(Product.class, id);

		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Session sess = sf.openSession();
		List<Product> products = null;
		
		try {
			//The use of HQL
			products = sess.createQuery("FROM users").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sess.close();
		}
		return products;
	}

	@Override
	public Product updateProduct(Product product) {
		Session sess = sf.openSession();
		Transaction tx = null;
		Product updatedProduct = null;
		
		try {
			tx = sess.beginTransaction();
			sess.merge(product);
			tx.commit();
			updatedProduct = product;
		} catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		
		return updatedProduct;
	}

	@Override
	public boolean deleteProduct(int id) {
		Session sess = sf.openSession();
		Transaction tx = null;
		
		try {
			tx = sess.beginTransaction();
			sess.delete(sess.get(Product.class, id));
			tx.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			sess.close();
		}
		return false;
	}
}
