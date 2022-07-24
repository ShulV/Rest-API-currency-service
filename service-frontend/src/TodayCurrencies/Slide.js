import React, { useState } from 'react'
import './css/style.css'

const Slide = (props) => {

    return (
        <div className='today-currencies__slide'>
            <div className='today-currencies__slide-charcode'>
                {props.currency.charcode}
            </div>
            <div className='today-currencies__slide-name'>
                ( {props.currency.name} )
            </div>
            <div className='today-currencies__slide-value'>
                {props.currency.value}
            </div>
         
        </div>
    );
}

export default Slide