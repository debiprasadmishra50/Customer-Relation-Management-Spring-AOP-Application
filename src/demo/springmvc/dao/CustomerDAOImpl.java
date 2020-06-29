package demo.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.springmvc.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject Hibernate SessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create a query ... sort by lastname
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
		
		// get the results from query by executing it and get result list
		List<Customer> customers = query.getResultList();
		
		// return results 
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// save the customer
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// retrieve the customer from database using the id/primary key
		Customer customer = session.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		
		// get the current Hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		
		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		// get the current session
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("rawtypes")
		Query query = null;
		
		// only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all customers
			query =session.createQuery("from Customer order by lastName", Customer.class);			
		}
		
		// execute query and get result list
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.getResultList();
						
		// return the results		
		return customers;
	}

}








