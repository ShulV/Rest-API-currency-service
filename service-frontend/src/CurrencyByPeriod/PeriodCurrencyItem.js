import React from 'react'
import './css/style.css'
import formatDate from '../utils/dateMethods'

const PeriodCurrencyItem = (props) => {


    return (
        <li>
            <div>{formatDate(new Date(parseInt(props.periodCurrency.date)))}</div>
            <div>{props.periodCurrency.value}</div>
        </li>
    )
}

export default PeriodCurrencyItem