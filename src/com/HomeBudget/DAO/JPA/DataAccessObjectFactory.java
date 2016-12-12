

package com.HomeBudget.DAO.JPA;


/**
 * Data access objects factory. All DAO factories must extend this class.
 *
 * @author Eng. shehab eldin tarek
 * @version 1.00
 */
public abstract class DataAccessObjectFactory {
    /*
     * Methods
     */

    /**
     * Get domain data access object implementation class.
     *
     * @return domain data access object implementation class.
     */
    public static DataAccessObject1 getDomainObjectDAO() {
        return new JPADataAccessObject();
    }
}