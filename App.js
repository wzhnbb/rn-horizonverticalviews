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
  TouchableWithoutFeedback
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
      "g3"
    let da = [
      {
        id: 1,
        items: [
          {id: 122, label: "label1", images: 
          ["http://image.kjt.com/mkt/Original/2017/0718/59866dbd-3227-4664-9217-2b3587e7c0c0.jpg",
           "http://image.kjt.com/mkt/Original/2017/0718/ef3d5be0-0fcc-4b14-9e7e-6aa74786701e.jpg", 
           "http://image.kjt.com/g1/M00/00/30/CgoJzVqoyFKAUbVSAAExNNqZBC8537.jpg"] },
          {id: 123, label: "label2", images:
           ["http://image.kjt.com/g1/M00/00/30/CgoJzVqoyFKAUbVSAAExNNqZBC8537.jpg", 
           "http://image.kjt.com/g1/M00/00/48/CgoJzVqvbVSAdmJ5AAEB-mTiJe4982.jpg"] }
        ]
      },
      {
        id: 2,
        items: [
          { id: 124, label: "label1", images: ["uri0", "uri1", "url2"] },
          {id: 125, label: "label2", images: ["uri3", "url4"] },
          { id: 126,label: "label3", images: ["uri5", "url6"] }
        ]
      }
    ]
      let data=["http://image.kjt.com/mkt/Original/2017/0718/59866dbd-3227-4664-9217-2b3587e7c0c0.jpg",
      "http://image.kjt.com/mkt/Original/2017/0718/ef3d5be0-0fcc-4b14-9e7e-6aa74786701e.jpg",
      "http://image.kjt.com/g1/M00/00/30/CgoJzVqoyFKAUbVSAAExNNqZBC8537.jpg",
      "http://image.kjt.com/g1/M00/00/48/CgoJzVqvbVSAdmJ5AAEB-mTiJe4982.jpg"]
      for(i=0;i<6;i++){
        this.state.dataList.push(data);
      }
  }





  render() {
    var myDate = new Date();
    return (
      <View style={styles.container}>
        <HorizonVertical ref={"hvView"} style={styles.instructions}
        key={myDate.getMilliseconds()}
        {...this.props}
          dataList={this.state.dataList}
          defaultIndex = {2}
          onPagePress={()=>{
            alert(11)
          }}
        ></HorizonVertical>
                <TouchableWithoutFeedback onPress={()=>{
                  this.refs["hvView"].changeCurrent(["https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=273140288,3209511839&fm=27&gp=0.jpg","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2977025261,3666758675&fm=27&gp=0.jpg"])
                  // this.setState({reLoad:true});
          // this.setState({dataList:[["file:///storage/emulated/0/.yjj/978de902d8d191cd77b0935f8d6af7a4.png","file:///storage/emulated/0/.yjj/31753e9d-b6b3-4817-b0f8-60fba00a3a80.jpg"],["file:///storage/emulated/0/.yjj/978de902d8d191cd77b0935f8d6af7a4.png","file:///storage/emulated/0/.yjj/31753e9d-b6b3-4817-b0f8-60fba00a3a80.jpg"]]})
          // this.setState({dataList:[["file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg","file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg"]
          // ,["file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg","file:///storage/emulated/0/.yjj/596f41ab3cacfb409b7f25546885c6f9.jpg"]],index:0})
          // this.refs["hvView"].AotuScroll('2000')
        }
          }>
          <View ><Text style={styles.welcome}>点我切换</Text></View></TouchableWithoutFeedback>
        <TouchableWithoutFeedback onPress={()=>{
          this.refs["hvView"].AotuScroll(2000)
        }
          }>
          <View ><Text style={styles.welcome}>点我开始</Text></View></TouchableWithoutFeedback>
          <TouchableWithoutFeedback onPress={()=>{
          this.refs["hvView"].StopScroll()
        }
          }>
          <View ><Text style={styles.welcome}>点我暂停</Text></View></TouchableWithoutFeedback>
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
    width:400,
    height:300,
    backgroundColor: 'white',
  },
});
