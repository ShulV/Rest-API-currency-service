import React, { useRef } from 'react'
import Slide from './Slide'
import cn from 'classnames'
import './css/style.css'

const Slider = (props) => {
    const slider = useRef(null)
    let prevAllowed = false
    let nextAllowed = true
    let position = 0

    const prevHandler = () => {
      if (prevAllowed) {
        position += 300
        slider.current.childNodes.forEach(element => {
          element.style.transform = `translateX(${position}px)`
        })
        nextAllowed = true
      }
      if (position === 0) {
        prevAllowed = false
        nextAllowed = true
      }

    }

    const nextHandler = () => {
      
      if (nextAllowed) {
        position -= 300
        slider.current.childNodes.forEach(element => {
          element.style.transform = `translateX(${position}px)`
        })
        prevAllowed = true
      }
      if (position === -(props.currencies.length - 3) * 300) {
        prevAllowed = true
        nextAllowed = false
      }
      
    }

    return (
      <div className='today-currencies__slider-wrapper'>
        <div className='today-currencies__slider' ref={slider}>
          { 
            props.currencies.map((currency, index) => {
               return (
                <Slide currency={currency} index={index} key={index}/>
               )
            })
          }
        </div>
        <button className={cn('today-currencies__slider-btn-next', 'today-currencies__slider-btn')} value='>' onClick={nextHandler}/>
        <button className={cn('today-currencies__slider-btn-prev', 'today-currencies__slider-btn')} value='<' onClick={prevHandler} />
      </div>
     
    );
}

export default Slider