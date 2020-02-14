// module "home.js"
import React, { Component, Fragment } from 'react'
import Axios from 'axios'

import DailyLifeGridList from 'Component/DailyLifeGridList'
import CategoryHelper from '../Component/CategoryHelper'


class Home extends Component {

  

  render() {
    Axios.get('/menu')
      .then(function (res) {
          setTailData(res.data)
      }).catch(function (error) {
          console.log(error)
      })
    return (
      <>
        {/* <CategoryHelper menu={} /> */}
        <DailyLifeGridList />
      </>
    )
  }
}

export default Home
