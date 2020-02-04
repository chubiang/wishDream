// module "home.js"
import React, { Component, Fragment } from 'react'

import DailyLifeGridList from 'Component/DailyLifeGridList'
import CategoryHelper from '../Component/CategoryHelper'


class Home extends Component {
  render() {
    return (
      <>
        <CategoryHelper />
        <DailyLifeGridList />
      </>
    )
  }
}

export default Home
