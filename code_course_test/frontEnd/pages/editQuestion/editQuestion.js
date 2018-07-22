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
    choiceNum:null,
    choiceContentNum: [],
    ifHaveChoice:[],
    correctAnswer: -1,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    var choiceContent = prevPage.data.choiceContent[options.key];
    if (!isNaN(choiceContent)){
      this.data.choiceContent.push("null");
      this.data.choiceContent.push("null");
      let contentNum = this.data.choiceContentNum;
      let head = [];
      let ifHave = [];
      contentNum.push(1);
      contentNum.push(1);
      head.push("A");
      head.push("B");
      ifHave.push(0);
      ifHave.push(0);
      this.setData({
        key: options.key,
        choiceContentNum: contentNum,
        content: prevPage.data.questionContent[options.key],
        choiceHead: head,
        choiceNum: 2,
        ifHaveChoice: ifHave
      });
    }
    else{
      let num = choiceContent.length;
      let contentNum = [];
      let head = [];
      let choice = [];
      let ifHave = [];
      var correct = parseInt(prevPage.data.correctAnswer);
      for (var i = 0; i < choiceContent.length; i++){
        head.push(String.fromCharCode(i + 65));
        choice.push(choiceContent[i]);
        contentNum.push(1);
        ifHave.push(1);
      }
      this.setData({
        key: options.key,
        choiceContentNum: contentNum,
        content: prevPage.data.questionContent[options.key],
        choiceHead: head,
        choiceNum: num,
        choiceContent: choice,
        ifHaveChoice: ifHave,
        correctAnswer: correct
      })
      console.log(this.data);
    }
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

  radioChange: function (e){
    var correct = e.detail.value;
    console.log(correct);
    this.setData({
      correctAnswer: correct
    })
  },

  confirm: function(){
    if (this.data.correctAnswer >= 0){
      let ifNull = -1;
      console.log(isNaN(this.data.choiceContent[0]));
      for (var i in this.data.choiceContent) {
        if (this.data.choiceContent[i].length == 0 || isNaN(this.data.choiceContent[i])) {
          ifNull = i;
          break;
        }
      }
      console.log(ifNull);
      if (ifNull < 0) {
        var pages = getCurrentPages();
        var prevPage = pages[pages.length - 2];
        var contentTemp = prevPage.data.choiceContent;
        var correct = prevPage.data.correctAnswer;
        contentTemp[this.data.key] = this.data.choiceContent;
        correct[this.data.key] = this.data.correctAnswer;
        prevPage.setData({
          choiceContent: contentTemp,
          correctAnswer: correct
        });
        wx.navigateBack({ changed: true });
      }
      else {
        wx.showModal({
          content: '选项' + this.data.choiceHead[ifNull] + '为空',
          showCancel: false
        })
      }
    }
    else{
      wx.showModal({
        content: '未确定正确选项',
        showCancel: false
      })
    }
  }
  

})