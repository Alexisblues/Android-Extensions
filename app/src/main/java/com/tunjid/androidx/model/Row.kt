package com.tunjid.androidx.model

import com.tunjid.androidx.recyclerview.diff.Differentiable

data class Row(
        val index: Int,
        val items: List<String>
) : Differentiable {
    val isHeader get() = items.first() == "Id"
    val idCell get() = Cell(isHeader, 0, items.first())
    val otherCells: List<Cell> get() = items.drop(1).mapIndexed { index, item -> Cell(isHeader, index + 1, item) }

    override val diffId: String get() = index.toString()
    override fun areContentsTheSame(other: Differentiable): Boolean =
            if (isHeader) false else super.areContentsTheSame(other)
}

data class Cell(
        val isHeader: Boolean,
        val column: Int,
        val text: String
) : Differentiable {
    override val diffId: String get() = column.toString()
    override fun areContentsTheSame(other: Differentiable): Boolean =
            if (isHeader) false else super.areContentsTheSame(other)
}