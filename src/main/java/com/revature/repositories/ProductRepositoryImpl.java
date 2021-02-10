package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.beans.Product;

public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	SessionFactory sf;

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

	@Override
	public <S extends Product> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Product> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Product> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
