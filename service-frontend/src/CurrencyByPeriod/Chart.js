import React from "react";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement
} from 'chart.js'
import { Line } from 'react-chartjs-2'

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement
)




const Chart = (props) => {


  return (
    <div className="currency-by-period__chart">
      <h2 className='section-header-2'>График</h2>
      <Line data={props.chartData} />
    </div>
    
  )
}
export default Chart