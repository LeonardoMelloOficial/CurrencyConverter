package repository


/**
 * A generic repository.
 * @author Leonardo Mello - leo@leonadomello.com
 */
interface Repository<T> {

    fun insert(obj : T) : T

    fun update (obj : T)


}