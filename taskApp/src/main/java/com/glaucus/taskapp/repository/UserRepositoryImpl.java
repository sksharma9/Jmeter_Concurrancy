package com.glaucus.taskapp.repository;

import javax.persistence.LockModeType;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.glaucus.taskapp.model.User;

import lombok.extern.log4j.Log4j;

@Log4j
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	private final LockOptions lockoptions = new LockOptions(LockMode.PESSIMISTIC_WRITE);

	public User updateUserCount(int id) {

		log.info("starting session...");
		
		Session session = HibernateUtil.getSession();

		Transaction transaction = null;
		
		User theUser = null;

		try {
			log.info("beginning the txn");
			
			transaction = session.beginTransaction();
			
			theUser = session.get(User.class, id,lockoptions);  // using pessimistic locking to lock concurrent updates.
			
			session.refresh(theUser);
				
					if (theUser != null) 
					{
							int currentCount = theUser.getCount();
							theUser.setCount(currentCount + 1);
					}
					
			transaction.commit();
			session.close();
		

		} 
		 catch (HibernateException hibernateException) {
				
				log.error("Excpetion while updating user's count for user with id: " + id);
				transaction.rollback();
				log.error("Exception: " + hibernateException);
			}
		
		catch (Exception exception) {
			
			transaction.rollback();
			log.error("Exception: " + exception);
		}
		finally {
			session.close();
		}

		return theUser;
	}

	public User getUser(int id) {

		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		User theUser = null;

		try {
			transaction = session.beginTransaction();

			theUser = session.get(User.class, id);

			transaction.commit();

			return theUser;

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			log.error("Excpetion while retreiving user's data for user with id: " + id);
			log.error("Exception: " + hibernateException);
		}
		finally {
			session.close();
		}
		return null;
	}

	public User addUser(String name) {

		Session session = HibernateUtil.getSession();
		Transaction transaction = null;
		User theUser = new User();

		theUser.setCount(0);
		theUser.setName(name);

		try {

			transaction = session.beginTransaction();

			session.save(theUser);

			transaction.commit();

			return theUser;

		} catch (HibernateException hibernateException) {
			transaction.rollback();
			log.error("exception occured while saving the user with id: " + theUser.getId());
			log.error("Exception: " + hibernateException);
		}
		finally {
			session.close();
		}

		return null;
	}

}
