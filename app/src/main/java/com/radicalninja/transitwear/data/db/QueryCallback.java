package com.radicalninja.transitwear.data.db;

import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

/**
 * Class for combining QueryTransaction callback, success, and error methods.
 * @param <T> A class extending Model in which this callback interacts with.
 */
public abstract class QueryCallback<T extends Model> implements
        QueryTransaction.QueryResultListCallback<T>, Transaction.Success, Transaction.Error {

    @Nullable public abstract Condition where();

    public Condition[] conditions(@Nullable final Condition ... conditions) {
        final Condition ourCondition = where();
        if (null == ourCondition) {
            return (null != conditions) ? conditions : new Condition[0];
        } else if (null == conditions) {
            return new Condition[] {ourCondition};
        } else {
            final Condition[] conditionList = new Condition[conditions.length + 1];
            System.arraycopy(conditions, 0, conditionList, 0, conditions.length);
            conditionList[conditions.length + 1] = ourCondition;
            return conditionList;
        }
    }

    /**
     * An empty implementation of QueryCallback for use when you don't need to override all methods.
     * @param <T> A class extending Model in which this callback interacts with.
     */
    public static class SimpleQueryCallback<T extends Model> extends QueryCallback<T> {
        @Nullable
        @Override
        public Condition where() {
            return null;
        }

        @Override
        public void onError(Transaction transaction, Throwable error) { }

        @Override
        public void onListQueryResult(QueryTransaction transaction, @Nullable List<T> tResult) { }

        @Override
        public void onSuccess(Transaction transaction) { }
    }

}
