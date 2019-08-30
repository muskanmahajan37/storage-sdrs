package com.google.gcs.sdrs.dao;

import org.hibernate.Session;

public interface LockDao {

  public Session getLockSession();

  public boolean obtainLock(String verificationKey, Session session);

  public boolean releaseLock(String verificationKey, Session session);

}
