import React from 'react'
import './css/style.css'
import formatDate from '../utils/dateMethods'

const PeriodCurrencyItem = (props) => {


    return (
        <li className="currency-by-period__list-li">
            <div className="currency-by-period__list-li-date">{formatDate(new Date(parseInt(props.periodCurrency.date)))}</div>
            <div className="currency-by-period__list-li-value">{props.periodCurrency.value} ₽</div>
        </li>
    )
}

export default PeriodCurrencyItem