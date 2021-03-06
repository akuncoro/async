package io.mifos.core.async.core;

import io.mifos.core.lang.TenantContextHolder;

public class DelegatingTenantContextRunnable implements Runnable {

  private final Runnable delegate;
  private final String tenantIdentifier;

  DelegatingTenantContextRunnable(final Runnable delegate) {
    this.delegate = delegate;
    this.tenantIdentifier = null;
  }

  DelegatingTenantContextRunnable(final Runnable delegate, final String tenantIdentifier) {
    super();
    this.delegate = delegate;
    this.tenantIdentifier = tenantIdentifier;
  }

  @Override
  public void run() {
    try {
      TenantContextHolder.clear();
      if(this.tenantIdentifier != null) {
        TenantContextHolder.setIdentifier(this.tenantIdentifier);
      }
      this.delegate.run();
    } finally {
      TenantContextHolder.clear();
    }
  }
}
