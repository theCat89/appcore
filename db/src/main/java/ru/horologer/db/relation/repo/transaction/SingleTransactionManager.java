package ru.horologer.db.relation.repo.transaction;

import ru.horologer.db.relation.model.CommonRepoResponse;
import ru.horologer.db.relation.model.InnerRepoRequest;
import ru.horologer.db.relation.model.TMRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.horologer.core.AppConfiguration.getLogger;

public class SingleTransactionManager {

    DBConnectionPool pool;

    public SingleTransactionManager setPool(DBConnectionPool pool) {
        this.pool = pool;
        return this;
    }

    public List<CommonRepoResponse> inOneTransaction(TMRequest... requests) {
        Connection connection = pool.getConnection();
        List<CommonRepoResponse> results = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            for (TMRequest request : requests) {
               try {
                   results.add(request.getFunction().apply(new InnerRepoRequest()
                           .setConn(connection)
                           .setRequest(request.getRequest())));
               } catch (BreakTransactionException e){
                   getLogger().log(e);
                   connection.rollback();
                   break;
               }
            }
            connection.commit();
        } catch (SQLException e) {
            getLogger().log(e);
        } finally {
            pool.freeConnection(connection);
        }
        return results;
    }

}
