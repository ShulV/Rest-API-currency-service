import React, { useState } from 'react'
import Form from './Form'
import Chart from './Chart'
import PeriodCurrencyList from './PeriodCurrencyList'


const CurrencyByPeriod = (props) => {

    const [periodCurrencies, setPeriodCurrencies] = useState([])
    const [selectOption, setSelectOption] = useState('USD')
    return (
        <div>
            <Form 
                currencies={props.currencies} 
                setPeriodCurrencies={setPeriodCurrencies} 
                periodCurrencies={periodCurrencies}
                selectOption={selectOption}
                setSelectOption={setSelectOption}
                className='currency-by-period__form'/>
            {/* <Chart currencies={props.currencies} /> */}
            <PeriodCurrencyList selectOption={selectOption} periodCurrencies={periodCurrencies} />
        </div>
    )
}

export default CurrencyByPeriod