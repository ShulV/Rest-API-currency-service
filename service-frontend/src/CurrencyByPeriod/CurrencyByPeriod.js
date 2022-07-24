import React from 'react'
import Form from './Form'

const CurrencyByPeriod = (props) => {

    return (
        <div>
            <Form currencies={props.currencies} className='currency-by-period__form'/>
        </div>
    )
}

export default CurrencyByPeriod