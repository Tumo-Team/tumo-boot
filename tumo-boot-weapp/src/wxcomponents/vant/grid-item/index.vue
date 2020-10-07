<template>
<uni-shadow-root class="vant-grid-item-index"><view :class="'custom-class '+(utils.bem('grid-item', { square }))" :style="viewStyle" @click="onClick">
  <view :class="'content-class '+(utils.bem('grid-item__content', [direction, { center, square, clickable, surround: border && gutter }]))+' '+(border ? 'van-hairline--surround' : '')" :style="contentStyle">
    <block v-if="useSlot">
      <slot></slot>
    </block>
    <block v-else>
      <view class="van-grid-item__icon icon-class">
        <van-icon v-if="icon" :name="icon" :color="iconColor" :dot="dot" :info="badge || info" :size="iconSize"></van-icon>
        <slot v-else name="icon"></slot>
      </view>
      <view class="van-grid-item__text text-class">
        <text v-if="text">{{ text }}</text>
        <slot v-else name="text"></slot>
      </view>
    </block>
  </view>
</view></uni-shadow-root>
</template>
<wxs src="../wxs/utils.wxs" module="utils"></wxs>
<script>
import VanIcon from '../icon/index.vue'
global['__wxVueOptions'] = {components:{'van-icon': VanIcon}}

global['__wxRoute'] = 'vant/grid-item/index'
import { link } from '../mixins/link';
import { VantComponent } from '../common/component';
import { addUnit } from '../common/utils';
VantComponent({
  relation: {
    name: 'grid',
    type: 'ancestor',
    current: 'grid-item',
  },
  classes: ['content-class', 'icon-class', 'text-class'],
  mixins: [link],
  props: {
    icon: String,
    iconColor: String,
    dot: Boolean,
    info: null,
    badge: null,
    text: String,
    useSlot: Boolean,
  },
  data: {
    viewStyle: '',
  },
  mounted() {
    this.updateStyle();
  },
  methods: {
    updateStyle() {
      if (!this.parent) {
        return;
      }
      const { data, children } = this.parent;
      const {
        columnNum,
        border,
        square,
        gutter,
        clickable,
        center,
        direction,
        iconSize,
      } = data;
      const width = `${100 / columnNum}%`;
      const styleWrapper = [];
      styleWrapper.push(`width: ${width}`);
      if (square) {
        styleWrapper.push(`padding-top: ${width}`);
      }
      if (gutter) {
        const gutterValue = addUnit(gutter);
        styleWrapper.push(`padding-right: ${gutterValue}`);
        const index = children.indexOf(this);
        if (index >= columnNum && !square) {
          styleWrapper.push(`margin-top: ${gutterValue}`);
        }
      }
      let contentStyle = '';
      if (square && gutter) {
        const gutterValue = addUnit(gutter);
        contentStyle = `
          right: ${gutterValue};
          bottom: ${gutterValue};
          height: auto;
        `;
      }
      this.setData({
        viewStyle: styleWrapper.join('; '),
        contentStyle,
        center,
        border,
        square,
        gutter,
        clickable,
        direction,
        iconSize,
      });
    },
    onClick() {
      this.$emit('click');
      this.jumpLink();
    },
  },
});
export default global['__wxComponents']['vant/grid-item/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-grid-item{position:relative;float:left;box-sizing:border-box}.van-grid-item--square{height:0}.van-grid-item__content{display:-webkit-flex;display:flex;-webkit-flex-direction:column;flex-direction:column;box-sizing:border-box;height:100%;padding:16px 8px;padding:var(--grid-item-content-padding,16px 8px);background-color:#fff;background-color:var(--grid-item-content-background-color,#fff)}.van-grid-item__content:after{z-index:1;border-width:0 1px 1px 0;border-bottom-width:var(--border-width-base,1px);border-right-width:var(--border-width-base,1px);border-top-width:0}.van-grid-item__content--surround:after{border-width:1px;border-width:var(--border-width-base,1px)}.van-grid-item__content--center{-webkit-align-items:center;align-items:center;-webkit-justify-content:center;justify-content:center}.van-grid-item__content--square{position:absolute;top:0;right:0;left:0}.van-grid-item__content--horizontal{-webkit-flex-direction:row;flex-direction:row}.van-grid-item__content--horizontal .van-grid-item__icon+.van-grid-item__text{margin-top:0;margin-left:8px}.van-grid-item__content--clickable:active{background-color:#f2f3f5;background-color:var(--grid-item-content-active-color,#f2f3f5)}.van-grid-item__icon{display:-webkit-flex;display:flex;-webkit-align-items:center;align-items:center;font-size:26px;font-size:var(--grid-item-icon-size,26px);height:26px;height:var(--grid-item-icon-size,26px)}.van-grid-item__text{word-wrap:break-word;color:#646566;color:var(--grid-item-text-color,#646566);font-size:12px;font-size:var(--grid-item-text-font-size,12px)}.van-grid-item__icon+.van-grid-item__text{margin-top:8px}
</style>