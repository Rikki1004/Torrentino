package com.rikkimikki.torrentino.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rikkimikki.torrentino.R
import com.rikkimikki.torrentino.databinding.ItemCategoryBinding
import com.rikkimikki.torrentino.domain.pojo.category.Category
import com.rikkimikki.torrentino.presentation.MainActivity
import com.rikkimikki.torrentino.presentation.ui.films.CategoriesViewModel


class CategoryAdapter(
    private val context: Context
    ) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback) {
    private var viewModel: CategoriesViewModel = ViewModelProvider(context as MainActivity)[CategoriesViewModel::class.java]

    var onAllFilmButtonClickListener: OnAllFilmButtonClickListener? = null
    var onFilmClickListener: OnFilmClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        println(viewType)
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category: Category = currentList[position]
        val adapter = FilmsAdapter()
        holder.textViewTitle.text = category.name
        holder.recyclerView.adapter = adapter
        viewModel.getFilms(category.graphName).observe(context as MainActivity){
            adapter.submitList(it.toMutableList())
        }

        adapter.onFilmClickListener = object :FilmsAdapter.OnFilmClickListener{
            override fun onFilmClick(id: Int, type: String) {
                onFilmClickListener?.onFilmClick(id,type)
            }
        }
        holder.textViewButtonAll.setOnClickListener {
            onAllFilmButtonClickListener?.onButtonClick(category.currentFilters.singleSelectFilterValues[0].value,category.name)
        }

        holder.recyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
    }

    class CategoryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var viewBinding = ItemCategoryBinding.bind(itemView)

        val textViewButtonAll = viewBinding.textViewShowAll
        val recyclerView = viewBinding.RecycleViewInnerCategories
        val textViewTitle = viewBinding.textViewCategoryName
    }

    interface OnFilmClickListener {
        fun onFilmClick(id: Int, type: String)
    }
    interface OnAllFilmButtonClickListener {
        fun onButtonClick(genre: String,title:String)
    }
    companion object{
        const val VIEW_TYPE = 1
    }
}
