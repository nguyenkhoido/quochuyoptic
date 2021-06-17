package com.vn.quochuyapplication.base

import android.content.Context
import android.util.SparseBooleanArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH> {
    private val mLock = Any()
    private var mObjects: MutableList<T>?
    var context: Context
        private set
    private var previousIndex = -1
    private var oldViewSelected: View? = null

    constructor(context: Context) {
        this.context = context
        mObjects = ArrayList()
    }

    constructor(context: Context, objects: MutableList<T>?) {
        this.context = context
        mObjects = objects
    }

    override fun getItemCount(): Int {
        return mObjects?.size?:0
    }

    fun getItem(position: Int): T {
        return mObjects?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    val isEmpty: Boolean
        get() = mObjects?.isEmpty() == true

    fun add(`object`: T) {
        synchronized(mLock) {
            val topIndex = mObjects?.size
            mObjects?.add(`object`)
            topIndex?.let { notifyItemInserted(it) }
        }
    }

    fun addAll(collection: Collection<T>?) {
        synchronized(mLock) {
            mObjects?.addAll(collection!!)
            notifyItemInserted(itemCount)
        }
    }

    @SafeVarargs
    fun addAll(vararg items: T) {
        synchronized(mLock) {
            Collections.addAll(mObjects, *items)
        }
        notifyDataSetChanged()
    }

    fun insert(`object`: T, index: Int) {
        synchronized(mLock) {
            mObjects?.add(index, `object`)
            notifyItemInserted(index)
        }
    }

    fun remove(`object`: T) {
        synchronized(mLock) {
            val position = getPosition(`object`)
            mObjects?.remove(`object`)
            notifyItemRemoved(position)
        }
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        synchronized(mLock) {
            if (position < itemCount) {
                mObjects?.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    fun clear() {
        synchronized(mLock) {
            mObjects?.clear()
            notifyDataSetChanged()
        }
    }

    fun getPosition(item: T): Int {
        return mObjects?.indexOf(item)!!
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained in this adapter.
     */
    fun sort(comparator: Comparator<in T>?) {
        Collections.sort(mObjects, comparator)
        notifyItemRangeChanged(0, itemCount)
    }

    /**
     * Gets data list.
     *
     * @return the data list
     */
    val dataList: List<T>
        get() = mObjects!!

    /**
     * Update.
     *
     * @param collection the collection
     */
    fun update(collection: Collection<T>?) {
        synchronized(mLock) {
            mObjects = ArrayList(collection!!)
            notifyDataSetChanged()
        }
    }

    fun refreshData(dataList: Collection<T>?) {
        clear()
        addAll(dataList)
    }

    //public abstract int getItemCount(int position);
    fun handleStateViewSelected(
        selectedItems: SparseBooleanArray,
        adapterPosition: Int,
        v: View
    ) { //item selected is current do nothing
        if (selectedItems[adapterPosition, false]) return
        if (adapterPosition != previousIndex) { //clear cache value in list
            if (selectedItems[previousIndex]) {
                selectedItems.delete(previousIndex)
                oldViewSelected!!.isSelected = false
            }
            //put new item selected into this boolean list and cache value
            selectedItems.put(adapterPosition, true)
            v.isSelected = true
            //cache previous index = current index, previous view = current view.
            previousIndex = adapterPosition
            oldViewSelected = v
        }
    }
}