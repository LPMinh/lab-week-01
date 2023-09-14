package com.example.labweek01.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public abstract class GenericCRUD<T> {
	protected SessionFactory sesssionFactory;
	public GenericCRUD(){
		sesssionFactory=MySessionFactory.getSessionFactory();
	}
	public boolean addObject(T t) {
		Transaction tr=null;
		try(Session session=sesssionFactory.openSession();){
			tr=session.beginTransaction();
			session.persist(t);
			tr.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	public boolean removeObject(T t) {
		Transaction tr=null;
		try(Session session=sesssionFactory.openSession()){
			tr=session.beginTransaction();
			session.remove(t);
			tr.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	public boolean updateObject(T t) {
		Transaction tr=null;
		try(Session session=sesssionFactory.openSession()){
			tr=session.beginTransaction();
			session.merge(t);
			tr.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}
	public T findObject(Object id,Class<T> clazz) {
		Transaction tr=null;
		try(Session session=sesssionFactory.openSession()){
			tr=session.beginTransaction();
			T object=session.find(clazz,id);
			tr.commit();
			return object;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tr.rollback();
		}
		return null;
	}
}
