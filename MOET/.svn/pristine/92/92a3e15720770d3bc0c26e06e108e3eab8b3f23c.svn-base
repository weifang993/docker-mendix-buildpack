/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package saml20.implementation.delegation;

import java.util.concurrent.TimeUnit;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

import saml20.implementation.common.Constants;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;

/**
 * @author Eric Dalquist
 * @version $Revision$
 */

@SuppressWarnings("rawtypes")
public class XPathExpressionPool implements XPathExpressionExecutor {
    protected final ILogNode logger = Core.getLogger(Constants.LOGNODE);
    
    private final GenericKeyedObjectPool pool;
    private final NamespaceContext namespaceContext;
    
    public XPathExpressionPool() {
        this(null);
    }
    
    @SuppressWarnings("unchecked")
	public XPathExpressionPool(NamespaceContext namespaceContext) {
        this.namespaceContext = namespaceContext;
        
        final XPathExpressionFactory xpathExpressionfactory = new XPathExpressionFactory();
        this.pool = new GenericKeyedObjectPool(xpathExpressionfactory);
        this.pool.setMaxActive(100);
        this.pool.setMaxIdle(100);
        this.pool.setTimeBetweenEvictionRunsMillis(TimeUnit.SECONDS.toMillis(60));
        this.pool.setMinEvictableIdleTimeMillis(TimeUnit.MINUTES.toMillis(5));
        this.pool.setNumTestsPerEvictionRun(this.pool.getMaxIdle() / 6);
    }
    
    @Override
    protected void finalize() throws Throwable {
        this.pool.close();
    }

    @SuppressWarnings("unchecked")
	public <T> T doWithExpression(String expression, XPathExpressionCallback<T> callback) throws XPathExpressionException {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Pooled expression " + expression + ": active=" + this.pool.getNumActive(expression) + ", idle=" +  this.pool.getNumIdle(expression));
        }
        
        try {
            final XPathExpression xPathExpression = (XPathExpression)this.pool.borrowObject(expression);
            try {
                return callback.doWithExpression(xPathExpression);
            }
            finally {
                this.pool.returnObject(expression, xPathExpression);
            }
        }
        catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException)e;
            }
            if (e instanceof XPathExpressionException) {
                throw (XPathExpressionException)e;
            }
            throw new IllegalStateException("Exception of type " + e.getClass().getName() + " is not expected", e);
        }
    }
    
    /* (non-Javadoc)
     * @see org.jasig.portal.security.provider.saml.XPathExpressionExecutor#evaluate(java.lang.String, java.lang.Object, javax.xml.namespace.QName)
     */
    @Override
	public <T> T evaluate(String expression, final Object item, final QName returnType) throws XPathExpressionException {
        return this.doWithExpression(expression, new XPathExpressionCallback<T>() {
            @Override
			@SuppressWarnings("unchecked")
            public T doWithExpression(XPathExpression xPathExpression) throws XPathExpressionException {
                return (T)xPathExpression.evaluate(item, returnType);
            }
        });
    }
    
    public interface XPathExpressionCallback<T> {
        T doWithExpression(XPathExpression xPathExpression) throws XPathExpressionException;
    }
    
	private class XPathExpressionFactory extends BaseKeyedPoolableObjectFactory {
        private final XPathFactory xPathFactory = XPathFactory.newInstance();
        
        @Override
        public synchronized Object makeObject(Object key) throws Exception {
            final String expression = (String)key;
            
            final XPath xPath = this.xPathFactory.newXPath();
            if (XPathExpressionPool.this.namespaceContext != null) {
                xPath.setNamespaceContext(XPathExpressionPool.this.namespaceContext);
            }
            
            XPathExpressionPool.this.logger.debug("Creating XPathExpression from: " + expression);
            
            return xPath.compile(expression);
        }

        @Override
        public void destroyObject(Object key, Object obj) throws Exception {
            final String expression = (String)key;
            XPathExpressionPool.this.logger.debug("Destroying XPathExpression: " + expression);
        }
    }
    
    public void clear() {
        this.pool.clear();
    }

    public void clearOldest() {
        this.pool.clearOldest();
    }

    public void close() throws Exception {
        this.pool.close();
    }

    public void evict() throws Exception {
        this.pool.evict();
    }

    public boolean getLifo() {
        return this.pool.getLifo();
    }

    public int getMaxActive() {
        return this.pool.getMaxActive();
    }

    public int getMaxIdle() {
        return this.pool.getMaxIdle();
    }

    public int getMaxTotal() {
        return this.pool.getMaxTotal();
    }

    public long getMaxWait() {
        return this.pool.getMaxWait();
    }

    public long getMinEvictableIdleTimeMillis() {
        return this.pool.getMinEvictableIdleTimeMillis();
    }

    public int getMinIdle() {
        return this.pool.getMinIdle();
    }

    public int getNumActive() {
        return this.pool.getNumActive();
    }

    public int getNumIdle() {
        return this.pool.getNumIdle();
    }

    public int getNumTestsPerEvictionRun() {
        return this.pool.getNumTestsPerEvictionRun();
    }

    public boolean getTestOnBorrow() {
        return this.pool.getTestOnBorrow();
    }

    public boolean getTestOnReturn() {
        return this.pool.getTestOnReturn();
    }

    public boolean getTestWhileIdle() {
        return this.pool.getTestWhileIdle();
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return this.pool.getTimeBetweenEvictionRunsMillis();
    }

    public byte getWhenExhaustedAction() {
        return this.pool.getWhenExhaustedAction();
    }

    public void setLifo(boolean lifo) {
        this.pool.setLifo(lifo);
    }

    public void setMaxActive(int maxActive) {
        this.pool.setMaxActive(maxActive);
    }

    public void setMaxIdle(int maxIdle) {
        this.pool.setMaxIdle(maxIdle);
    }

    public void setMaxTotal(int maxTotal) {
        this.pool.setMaxTotal(maxTotal);
    }

    public void setMaxWait(long maxWait) {
        this.pool.setMaxWait(maxWait);
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.pool.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    }

    public void setMinIdle(int poolSize) {
        this.pool.setMinIdle(poolSize);
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.pool.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.pool.setTestOnBorrow(testOnBorrow);
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.pool.setTestOnReturn(testOnReturn);
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.pool.setTestWhileIdle(testWhileIdle);
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.pool.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    }

    public void setWhenExhaustedAction(byte whenExhaustedAction) {
        this.pool.setWhenExhaustedAction(whenExhaustedAction);
    }
}
