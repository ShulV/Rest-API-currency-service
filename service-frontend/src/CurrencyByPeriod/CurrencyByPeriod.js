import React from 'react'
import Form from './Form'
import Chart from './Chart'


const CurrencyByPeriod = (props) => {

    return (
        <div>
            <Form currencies={props.currencies} className='currency-by-period__form'/>
            <Chart currencies={props.currencies} />
        </div>
    )
}

export default CurrencyByPeriod