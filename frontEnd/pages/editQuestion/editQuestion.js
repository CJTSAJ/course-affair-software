// editQuestion.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    key: null,
    content: null,
    choiceContent: [],
    choiceHead:[],
    choiceNum:2,
    choiceContentNum: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.choiceContent.push("null");
    this.data.choiceContent.push("null");
    let num = this.data.choiceContentNum;
    let head = [];
    num.push(1);
    num.push(1);
    head.push("A");
    head.push("B");
    this.setData({
      key: options.key,
      choiceContentNum: num,
      content: options.content,
      choiceHead: head
    });
  },

  addChoice: function(){
    this.data.choiceContent.push("null");
    let contentNum = this.data.choiceContentNum;
    let num = this.data.choiceNum;
    num = num + 1;
    let choiceHead = this.data.choiceHead;
    choiceHead.push(String.fromCharCode(num + 64));
    contentNum.push(1);
    this.setData({
      choiceContentNum: contentNum,
      choiceNum: num,
      choiceHead: choiceHead
    });
    console.log(this.data);
  },

  inputChoiceContent: function(e){
    let choice = this.data.choiceContent;
    choice[e.target.id] = e.detail.value;
    this.setData({
      choiceContent: choice
    })
    console.log(this.data.choiceContent);
  },

  confirm: function(){
    let ifNull = -1;
    for (var i in this.data.choiceContent){
      if (isNaN(this.data.choiceContent[i]) ||  this.data.choiceContent[i].length == 0){
        ifNull = i;
        break;
      }
    }
    console.log(ifNull);
    if(ifNull < 0){
      var pages = getCurrentPages();
      var prevPage = pages[pages.length - 2];
      var contentTemp = prevPage.data.choiceContent;
      contentTemp[this.data.key] = this.data.choiceContent;
      prevPage.setData({
        choiceContent: contentTemp
      });
    }
    else{
      wx.showModal({
        content: '选项' + this.data.choiceHead[ifNull] + '为空',
      })
    }
  }
  
})