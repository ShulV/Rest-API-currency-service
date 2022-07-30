import React from 'react'

const Option = (props) => {
    return (
        <option className='currency-by-period__option' data-charcode={props.currency.charcode}>
            {props.currency.name}
        </option>
    )
}

export default Option