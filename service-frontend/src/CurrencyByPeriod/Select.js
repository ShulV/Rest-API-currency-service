import React from 'react'
import Option from './Option'
import './css/style.css'

const Select = (props) => {

    return (
        
        <select className='currency-by-period__select' onChange={props.selectOptionHandler}>
            { 
                props.currencies.map((currency, index) => {
                    return (
                     <Option currency={currency} index={index} key={index}/>
                    )
                 })
            }
        </select>
    )
}

export default Select