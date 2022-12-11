package trying.cosmos.global.extension

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.domain.SliceImpl

fun <T> MutableList<T>.toSlice(pageable: Pageable): Slice<T> {
    val hasNext = this.size > pageable.pageSize
    if (hasNext) {
        this.removeAt(pageable.pageSize)
    }

    return SliceImpl(this, pageable, hasNext)
}