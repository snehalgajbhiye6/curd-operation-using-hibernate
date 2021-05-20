package com.app.entity;

import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Management {
    private static SessionFactory sf;
	Boolean flag=Boolean.FALSE;

    Scanner sc=new Scanner(System.in);
    static {
    	Configuration cfg=new Configuration().configure();
    	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder();
    	builder.applySettings(cfg.getProperties());
    	ServiceRegistry sr= builder.build();
    	sf=cfg.buildSessionFactory(sr);	
    }
    
    public void insert() {
    	Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		System.out.println("How many student do you want?");
		int noOfemp=sc.nextInt();
		for(int i=0;i<noOfemp;i++)
		{
			Student s=new Student();
			System.out.println("Enter name");
			s.setName(sc.next());
			
			System.out.println("Enter address");
			s.setAddress(sc.next());
			
			session.persist(s);
		}
		tr.commit();
		flag=tr.wasCommitted();
		if(flag)
			System.out.println("Succesfull");
		else
			System.out.println("Interupted");
    }
    public void delete() {
    	Session session=sf.openSession();
    	Transaction tr=session.beginTransaction();
    	System.out.println("which record do you want to delete");
    	int id=sc.nextInt();
    	Student s=(Student)session.get(Student.class, id);
    	session.delete(s);
    	tr.commit();
    	flag=tr.wasCommitted();
    	if(flag)
			System.out.println("Succesfull");
		else
			System.out.println("Interupted");
    }
    
    public void update() {
    	Session session=sf.openSession();
    	Transaction tr=session.beginTransaction();
    	System.out.println("Which record do you want to update");
    	int id=sc.nextInt();
    	Student s=(Student)session.load(Student.class, id);
    	System.out.println("Change name to");
    	s.setName(sc.next());
    	session.update(s);
    	tr.commit();
    	flag=tr.wasCommitted();
    	if(flag)
			System.out.println("Succesfull");
		else
			System.out.println("Interupted");
    }
    
    @SuppressWarnings("unchecked")
	public void select() {
    	Session session=sf.openSession();
    	Criteria cr=session.createCriteria(Student.class);
    	cr.list().forEach(System.out::println);
    }
    public static void main(String args[]) {
    	Management m=new Management();
    	//m.insert();
    	//m.delete();
    	//m.update();
    	m.select();
    }
}
