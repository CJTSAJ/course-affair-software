const app = getApp()
// pages/managerSetting/mangerSetting.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allMember: ['小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红', '小明', '李华', '小红'],
    selectedMember: []
  },

  onShow:function(){
    /*向后端获取该群内所有学生名单*/
    var self = this;
     wx.request({
       url: 'http://localhost:8080/getStudentByGid',
       data:{
         opengid: app.globalData.openGId
       },
       method: 'POST',
       header: { 'content-type': 'application/json' },
       success:function(res){
         console.log(res.data[0]);
         self.setData({
           allMember: res.data
         })
       }
     })
  },

  confirm:function(){
    //向后端发送数据
    var selected = this.data.selectedMember;
    var all = this.data.allMember;
    var openidArray = [];
    for (var i = 0; i < selected.length; i++){
      openidArray.push(all[selected[i]].openid);
    }
    console.log(openidArray);
    
    wx.request({
      url: 'http://localhost:8080/addManager',
      data: {
        opengid: app.globalData.openGId,
        openidArray: openidArray
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(){

      }
    })
  },
  checkboxChange:function(e){
    var selected = e.detail.value;
    console.log(selected)
    this.setData({
      selectedMember: selected
    })
  }
})