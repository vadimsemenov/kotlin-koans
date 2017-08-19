package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int =
            compareValuesBy(this, other, MyDate::year, MyDate::month, MyDate::dayOfMonth)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        private var next = start
        override fun hasNext(): Boolean = next <= endInclusive

        override fun next(): MyDate {
            val ret = next
            next = next.addTimeIntervals(TimeInterval.DAY, 1)
            return ret
        }
    }
}
