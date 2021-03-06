package com.faq.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.faq.app.model.Faq;

public class DatabaseOperations {
	// PERSISTENCE_UNIT_NAME is the name recorded in the persistence.xml file
	private static final String PERSISTENCE_UNIT_NAME = "GroupKOPS_COMP303_Assignment3_JPA";
	private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
			.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	public static List<Faq> getAllFaqRecords() {
		TypedQuery<Faq> query = em.createNamedQuery("Faq.findAll", Faq.class);
		return getFaqQueryResults(query);
	}

	public static List<Faq> getAllFaqRecordsWithTopicNameContaining(String topicName) {
		TypedQuery<Faq> query = em.createNamedQuery("Faq.findByContainingTopicName", Faq.class);
		query.setParameter("topicName", topicName);
		return getFaqQueryResults(query);
	}

	// Helper functions
	private static List<Faq> getFaqQueryResults(TypedQuery<Faq> query) {
		List<Faq> faqList = query.getResultList();
		if (faqList != null && faqList.size() > 0) {
			return faqList;
		} else {
			return null;
		}
	}
}
