import React from 'react'
import PeriodCurrencyItem from './PeriodCurrencyItem'
import './css/style.css'

const PeriodCurrencyList = (props) => {

    return (
        <div>
            <h2>Курс {props.selectOption} за период</h2>
            <ul>
            {   
                props.periodCurrencies.map((periodCurrency, index) => {
                    return (
                        <PeriodCurrencyItem periodCurrency={periodCurrency} index={index} key={index}/>
                    )
                })
            }
            </ul>
        </div>
        
    )
}

export default PeriodCurrencyList