/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  Alert,
  DeviceEventEmitter,TouchableWithoutFeedback
} from 'react-native';
import HorizonVertical from './comm/HorizonVerticalView'


const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  state={
    reLoad : false,
    dataList : [],
    index:1
  }
  componentWillMount(){
      //      //监听事件名为EventName的事件  
      //      DeviceEventEmitter.addListener('onPageScroll', (e)=> {  
      //       //e是原生传过来的参数  
      //       console.log(e)  
      //       // alert("滑动了");  
      //   }); 
      //   DeviceEventEmitter.addListener('onPagePress', (e)=> {  
      //     //e是原生传过来的参数  
      //     console.log(e)  
      //     // alert("点击了");  
      // }); 
      let data=["http://image.kjt.com/mkt/Original/2017/0718/59866dbd-3227-4664-9217-2b3587e7c0c0.jpg",
      "http://image.kjt.com/mkt/Original/2017/0718/ef3d5be0-0fcc-4b14-9e7e-6aa74786701e.jpg",
      "http://image.kjt.com/g1/M00/00/30/CgoJzVqoyFKAUbVSAAExNNqZBC8537.jpg",
      "http://image.kjt.com/g1/M00/00/48/CgoJzVqvbVSAdmJ5AAEB-mTiJe4982.jpg"]
      for(i=0;i<5;i++){
        this.state.dataList.push(data);
      }
  }





  render() {

    return (
      <View style={styles.container}>
        <HorizonVertical ref={"hvView"} style={styles.instructions}
        {...this.props}
          dataList={this.state.dataList}
          defaultIndex = {1}
          onPagePress={()=>{
            alert(11)
          }}
        ></HorizonVertical>
        <TouchableWithoutFeedback onPress={()=>{
          // this.setState({dataList:[["file:///storage/emulated/0/.yjj/978de902d8d191cd77b0935f8d6af7a4.png","file:///storage/emulated/0/.yjj/31753e9d-b6b3-4817-b0f8-60fba00a3a80.jpg"],["file:///storage/emulated/0/.yjj/978de902d8d191cd77b0935f8d6af7a4.png","file:///storage/emulated/0/.yjj/31753e9d-b6b3-4817-b0f8-60fba00a3a80.jpg"]]})
          // this.refs["hvView"].changeCurrent(["file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg","file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg"])
          // this.setState({dataList:[["file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg","file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg"]
          // ,["file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg","file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg"]],index:0})
          this.refs["hvView"].changeCurrent(["http://image.kjt.com/neg/P450/3714f8ae-14a3-4771-8e4d-a9d4dd1bf824.jpg","http://image.kjt.com/neg/P450/a68124f7-45a0-4254-bf1a-19ea5ea387bb.jpg"])
        }
          }>
          <View ><Text style={styles.welcome}>点我</Text></View></TouchableWithoutFeedback>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#999',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    width:600,
    height:400,
    backgroundColor: 'white',
  },
});
