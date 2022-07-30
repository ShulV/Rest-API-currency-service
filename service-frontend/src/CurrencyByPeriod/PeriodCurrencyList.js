import React from 'react'
import PeriodCurrencyItem from './PeriodCurrencyItem'
import './css/style.css'

const PeriodCurrencyList = (props) => {

    return (
        <div className='currency-by-period__list'>
            <h2 className='section-header-2'>Курс {props.selectOption} за период</h2>
            <ul className='currency-by-period__list-ul'>
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