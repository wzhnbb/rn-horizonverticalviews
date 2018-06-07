/**
 * Created by lijie on 16/7/13.
 */
'use strict';

import PropTypes from 'prop-types'
import React, {PureComponent } from 'react';
import { requireNativeComponent, View ,DeviceEventEmitter,NativeModules,UIManager} from 'react-native';
const ReactNative = require('ReactNative');
var  HorizonVerticalViewEventModule = UIManager.RCTAndroidHorizonVerticalView;
var iface = {
    name: 'HorizonVerticalView',
    propTypes: {
        ...View.propTypes,
        dataList: PropTypes.array,
        defaultIndex:PropTypes.number,
        defaultImg:PropTypes.string
    },
};

var RCTHorizonVerticalView = requireNativeComponent('RCTAndroidHorizonVerticalView', iface);
export default class HorizonVertical extends PureComponent {
    constructor() {
        super();
        let self = this;
      }
      static propTypes = {
        ...View.propTypes,
        dataList: PropTypes.array,
        defaultIndex:PropTypes.number,
        defaultImg:PropTypes.string,
        onPageScroll:PropTypes.func,
        onPagePress:PropTypes.func,
    }
    getViewHandle = () => {
      return ReactNative.findNodeHandle(this.refs["RCTHorizonVerticalView"]);
    };
    //componentDidMount componentWillMount
    componentDidMount(){
      //横向滑动
        DeviceEventEmitter.addListener('ontHorizonPageScroll', (e)=> {  
          console.log(e)
        }); 
        //纵向滑动
        DeviceEventEmitter.addListener('onVerticalPageScroll', (e)=> {  
          console.log(e)
        }); 
        DeviceEventEmitter.addListener('onPagePress', (e)=> {  
          console.log(e)
          //e是原生传过来的参数  
          if(this.props.onPagePress&&typeof(this.props.onPagePress)=='function'){
            this.props.onPagePress();
          }
      }); 
      }
      changeCurrent(data){
        // this.RCTAndroidHorizonVerticalView.Commands.changeCurrent(data);
        UIManager.dispatchViewManagerCommand(
          this.getViewHandle(),
          UIManager.RCTAndroidHorizonVerticalView.Commands.changeCurrent,
          data,
        );
        // console.log(NativeModules.HorizonVerticalViewEventModule);
        // HorizonVerticalViewEventModule.changeCurrent(data)
      }
      render() {
        return <RCTHorizonVerticalView
        ref={"RCTHorizonVerticalView"}
          {...this.props}
           />
      }
}
module.exports = HorizonVertical