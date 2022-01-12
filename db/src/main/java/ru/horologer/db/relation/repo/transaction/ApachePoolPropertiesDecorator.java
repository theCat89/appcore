package ru.horologer.db.relation.repo.transaction;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.apache.tomcat.jdbc.pool.Validator;

import java.util.Properties;

public class ApachePoolPropertiesDecorator {

    PoolProperties p = new PoolProperties();

    public ApachePoolPropertiesDecorator setAbandonWhenPercentageFull(int percentage) {
        p.setAbandonWhenPercentageFull(percentage);
        return this;
    }

    public int getAbandonWhenPercentageFull() {
        return p.getAbandonWhenPercentageFull();
    }

    public boolean isFairQueue() {
        return p.isFairQueue();
    }

    public ApachePoolPropertiesDecorator setFairQueue(boolean fairQueue) {
        p.setFairQueue(fairQueue);
        return this;
    }

    public boolean isAccessToUnderlyingConnectionAllowed() {
        return p.isAccessToUnderlyingConnectionAllowed();
    }

    public String getConnectionProperties() {
        return p.getConnectionProperties();
    }

    public Properties getDbProperties() {
        return p.getDbProperties();
    }

    public Boolean isDefaultAutoCommit() {
        return p.isDefaultAutoCommit();
    }

    public String getDefaultCatalog() {
        return p.getDefaultCatalog();
    }

    public Boolean isDefaultReadOnly() {
        return p.isDefaultReadOnly();
    }

    public int getDefaultTransactionIsolation() {
        return p.getDefaultTransactionIsolation();
    }

    public String getDriverClassName() {
        return p.getDriverClassName();
    }

    public int getInitialSize() {
        return p.getInitialSize();
    }

    public boolean isLogAbandoned() {
        return p.isLogAbandoned();
    }

    public int getMaxActive() {
        return p.getMaxActive();
    }

    public int getMaxIdle() {
        return p.getMaxIdle();
    }

    public int getMaxWait() {
        return p.getMaxWait();
    }

    public int getMinEvictableIdleTimeMillis() {
        return p.getMinEvictableIdleTimeMillis();
    }

    public int getMinIdle() {
        return p.getMinIdle();
    }

    public String getName() {
        return p.getName();
    }

    public int getNumTestsPerEvictionRun() {
        return p.getNumTestsPerEvictionRun();
    }

    public String getPassword() {
        return p.getPassword();
    }

    public String getPoolName() {
        return p.getPoolName();
    }

    public boolean isRemoveAbandoned() {
        return p.isRemoveAbandoned();
    }

    public int getRemoveAbandonedTimeout() {
        return p.getRemoveAbandonedTimeout();
    }

    public boolean isTestOnBorrow() {
        return p.isTestOnBorrow();
    }

    public boolean isTestOnReturn() {
        return p.isTestOnReturn();
    }

    public boolean isTestWhileIdle() {
        return p.isTestWhileIdle();
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return p.getTimeBetweenEvictionRunsMillis();
    }

    public String getUrl() {
        return p.getUrl();
    }

    public String getUsername() {
        return p.getUsername();
    }

    public String getValidationQuery() {
        return p.getValidationQuery();
    }

    public int getValidationQueryTimeout() {
        return p.getValidationQueryTimeout();
    }

    public ApachePoolPropertiesDecorator setValidationQueryTimeout(int validationQueryTimeout) {
        p.setValidationQueryTimeout(validationQueryTimeout);
        return this;
    }

    public String getValidatorClassName() {
        return p.getValidatorClassName();
    }

    public Validator getValidator() {
        return p.getValidator();
    }

    public ApachePoolPropertiesDecorator setValidator(Validator validator) {
        p.setValidator(validator);
        return this;
    }

    public long getValidationInterval() {
        return p.getValidationInterval();
    }

    public String getInitSQL() {
        return p.getInitSQL();
    }

    public boolean isTestOnConnect() {
        return p.isTestOnConnect();
    }

    public String getJdbcInterceptors() {
        return p.getJdbcInterceptors();
    }

    public PoolProperties.InterceptorDefinition[] getJdbcInterceptorsAsArray() {
        return p.getJdbcInterceptorsAsArray();
    }

    public ApachePoolPropertiesDecorator setAccessToUnderlyingConnectionAllowed(boolean accessToUnderlyingConnectionAllowed) {
        p.setAccessToUnderlyingConnectionAllowed(accessToUnderlyingConnectionAllowed);
        return this;
    }

    public ApachePoolPropertiesDecorator setConnectionProperties(String connectionProperties) {
        p.setConnectionProperties(connectionProperties);
        return this;
    }

    public ApachePoolPropertiesDecorator setDbProperties(Properties dbProperties) {
        p.setDbProperties(dbProperties);
        return this;
    }

    public ApachePoolPropertiesDecorator setDefaultAutoCommit(Boolean defaultAutoCommit) {
        p.setDefaultAutoCommit(defaultAutoCommit);
        return this;
    }

    public ApachePoolPropertiesDecorator setDefaultCatalog(String defaultCatalog) {
        p.setDefaultCatalog(defaultCatalog);
        return this;
    }

    public ApachePoolPropertiesDecorator setDefaultReadOnly(Boolean defaultReadOnly) {
        p.setDefaultReadOnly(defaultReadOnly);
        return this;
    }

    public ApachePoolPropertiesDecorator setDefaultTransactionIsolation(int defaultTransactionIsolation) {
        p.setDefaultTransactionIsolation(defaultTransactionIsolation);
        return this;
    }

    public ApachePoolPropertiesDecorator setDriverClassName(String driverClassName) {
        p.setDriverClassName(driverClassName);
        return this;
    }

    public ApachePoolPropertiesDecorator setInitialSize(int initialSize) {
        p.setInitialSize(initialSize);
        return this;
    }

    public ApachePoolPropertiesDecorator setLogAbandoned(boolean logAbandoned) {
        p.setLogAbandoned(logAbandoned);
        return this;
    }

    public ApachePoolPropertiesDecorator setMaxActive(int maxActive) {
        p.setMaxActive(maxActive);
        return this;
    }

    public ApachePoolPropertiesDecorator setMaxIdle(int maxIdle) {
        p.setMaxIdle(maxIdle);
        return this;
    }

    public ApachePoolPropertiesDecorator setMaxWait(int maxWait) {
        p.setMaxWait(maxWait);
        return this;
    }

    public ApachePoolPropertiesDecorator setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        p.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        return this;
    }

    public ApachePoolPropertiesDecorator setMinIdle(int minIdle) {
        p.setMinIdle(minIdle);
        return this;
    }

    public ApachePoolPropertiesDecorator setName(String name) {
        p.setName(name);
        return this;
    }

    public ApachePoolPropertiesDecorator setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        p.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        return this;
    }

    public ApachePoolPropertiesDecorator setPassword(String password) {
        p.setPassword(password);
        return this;
    }

    public ApachePoolPropertiesDecorator setRemoveAbandoned(boolean removeAbandoned) {
        p.setRemoveAbandoned(removeAbandoned);
        return this;
    }

    public ApachePoolPropertiesDecorator setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
        p.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        return this;
    }

    public ApachePoolPropertiesDecorator setTestOnBorrow(boolean testOnBorrow) {
        p.setTestOnBorrow(testOnBorrow);
        return this;
    }

    public ApachePoolPropertiesDecorator setTestWhileIdle(boolean testWhileIdle) {
        p.setTestWhileIdle(testWhileIdle);
        return this;
    }

    public ApachePoolPropertiesDecorator setTestOnReturn(boolean testOnReturn) {
        p.setTestOnReturn(testOnReturn);
        return this;
    }

    public ApachePoolPropertiesDecorator setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        p.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        return this;
    }

    public ApachePoolPropertiesDecorator setUrl(String url) {
        p.setUrl(url);
        return this;
    }

    public ApachePoolPropertiesDecorator setUsername(String username) {
        p.setUsername(username);
        return this;
    }

    public ApachePoolPropertiesDecorator setValidationInterval(long validationInterval) {
        p.setValidationInterval(validationInterval);
        return this;
    }

    public ApachePoolPropertiesDecorator setValidationQuery(String validationQuery) {
        p.setValidationQuery(validationQuery);
        return this;
    }

    public ApachePoolPropertiesDecorator setValidatorClassName(String className) {
        p.setValidatorClassName(className);
        return this;
    }

    public ApachePoolPropertiesDecorator setInitSQL(String initSQL) {
        p.setInitSQL(initSQL);
        return this;
    }

    public ApachePoolPropertiesDecorator setTestOnConnect(boolean testOnConnect) {
        p.setTestOnConnect(testOnConnect);
        return this;
    }

    public ApachePoolPropertiesDecorator setJdbcInterceptors(String jdbcInterceptors) {
        p.setJdbcInterceptors(jdbcInterceptors);
        return this;
    }


    public static int getPoolCounter() {
        return PoolProperties.getPoolCounter();
    }

    public boolean isJmxEnabled() {
        return p.isJmxEnabled();
    }

    public ApachePoolPropertiesDecorator setJmxEnabled(boolean jmxEnabled) {
        p.setJmxEnabled(jmxEnabled);
        return this;
    }

    public Boolean getDefaultAutoCommit() {
        return p.getDefaultAutoCommit();
    }

    public Boolean getDefaultReadOnly() {
        return p.getDefaultReadOnly();
    }

    public int getSuspectTimeout() {
        return p.getSuspectTimeout();
    }

    public ApachePoolPropertiesDecorator setSuspectTimeout(int seconds) {
        p.setSuspectTimeout(seconds);
        return this;
    }

    public boolean isPoolSweeperEnabled() {
        return p.isPoolSweeperEnabled();
    }

    public boolean isUseEquals() {
        return p.isUseEquals();
    }

    public ApachePoolPropertiesDecorator setUseEquals(boolean useEquals) {
        p.setUseEquals(useEquals);
        return this;
    }

    public long getMaxAge() {
        return p.getMaxAge();
    }

    public ApachePoolPropertiesDecorator setMaxAge(long maxAge) {
        p.setMaxAge(maxAge);
        return this;
    }

    public boolean getUseLock() {
        return p.getUseLock();
    }

    public ApachePoolPropertiesDecorator setUseLock(boolean useLock) {
        p.setUseLock(useLock);
        return this;
    }

    public ApachePoolPropertiesDecorator setDataSource(Object ds) {
        p.setDataSource(ds);
        return this;
    }

    public Object getDataSource() {
        return p.getDataSource();
    }

    public ApachePoolPropertiesDecorator setDataSourceJNDI(String jndiDS) {
        p.setDataSourceJNDI(jndiDS);
        return this;
    }

    public String getDataSourceJNDI() {
        return p.getDataSourceJNDI();
    }

    public static Properties getProperties(String propText, Properties props) {
        return PoolProperties.getProperties(propText, props);
    }

    public boolean isAlternateUsernameAllowed() {
        return p.isAlternateUsernameAllowed();
    }

    public ApachePoolPropertiesDecorator setAlternateUsernameAllowed(boolean alternateUsernameAllowed) {
        p.setAlternateUsernameAllowed(alternateUsernameAllowed);
        return this;
    }

    public ApachePoolPropertiesDecorator setCommitOnReturn(boolean commitOnReturn) {
        p.setCommitOnReturn(commitOnReturn);
        return this;
    }

    public boolean getCommitOnReturn() {
        return p.getCommitOnReturn();
    }

    public ApachePoolPropertiesDecorator setRollbackOnReturn(boolean rollbackOnReturn) {
        p.setRollbackOnReturn(rollbackOnReturn);
        return this;
    }

    public boolean getRollbackOnReturn() {
        return p.getRollbackOnReturn();
    }

    public ApachePoolPropertiesDecorator setUseDisposableConnectionFacade(boolean useDisposableConnectionFacade) {
        p.setUseDisposableConnectionFacade(useDisposableConnectionFacade);
        return this;
    }

    public boolean getUseDisposableConnectionFacade() {
        return p.getUseDisposableConnectionFacade();
    }

    public ApachePoolPropertiesDecorator setLogValidationErrors(boolean logValidationErrors) {
        p.setLogValidationErrors(logValidationErrors);
        return this;
    }

    public boolean getLogValidationErrors() {
        return p.getLogValidationErrors();
    }

    public boolean getPropagateInterruptState() {
        return p.getPropagateInterruptState();
    }

    public ApachePoolPropertiesDecorator setPropagateInterruptState(boolean propagateInterruptState) {
        p.setPropagateInterruptState(propagateInterruptState);
        return this;
    }

    public boolean isIgnoreExceptionOnPreLoad() {
        return p.isIgnoreExceptionOnPreLoad();
    }

    public ApachePoolPropertiesDecorator setIgnoreExceptionOnPreLoad(boolean ignoreExceptionOnPreLoad) {
        p.setIgnoreExceptionOnPreLoad(ignoreExceptionOnPreLoad);
        return this;
    }

    public boolean getUseStatementFacade() {
        return p.getUseStatementFacade();
    }

    public ApachePoolPropertiesDecorator setUseStatementFacade(boolean useStatementFacade) {
        p.setUseStatementFacade(useStatementFacade);
        return this;
    }

    public PoolProperties getP() {
        return p;
    }

    public ApachePoolPropertiesDecorator setP(PoolProperties p) {
        this.p = p;
        return this;
    }
}
