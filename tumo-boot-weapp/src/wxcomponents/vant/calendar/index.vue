<template>
<uni-shadow-root class="vant-calendar-index"><van-popup v-if="poppable" :custom-class="'van-calendar__popup--'+(position)" close-icon-class="van-calendar__close-icon" :show="show" :round="round" :position="position" :closeable="showTitle || showSubtitle" :close-on-click-overlay="closeOnClickOverlay" @enter="onOpen" @close="onClose" @after-enter="onOpened" @after-leave="onClosed">
  <calendar v-bind="{title, subtitle, showTitle, showSubtitle, minDate, maxDate, type, color, showMark, formatter, rowHeight, currentDate, safeAreaInsetBottom, showConfirm, confirmDisabledText, confirmText, scrollIntoView, allowSameDay}" wx-template-name="calendar"></calendar>
</van-popup>

<calendar v-bind="{title, subtitle, showTitle, showSubtitle, minDate, maxDate, type, color, showMark, formatter, rowHeight, currentDate, safeAreaInsetBottom, showConfirm, confirmDisabledText, confirmText, scrollIntoView, allowSameDay}" v-else wx-template-name="calendar"></calendar>

<van-toast id="van-toast"></van-toast></uni-shadow-root>
</template>
<wxs src="./index.wxs" module="computed"></wxs>
<script>

const __wxTemplateComponentProps = {"calendar":["wxTemplateName","title","subtitle","showTitle","showSubtitle","minDate","maxDate","type","color","showMark","formatter","rowHeight","currentDate","safeAreaInsetBottom","showConfirm","confirmDisabledText","confirmText","scrollIntoView","allowSameDay"]}
import __wxTemplateComponent0 from './calendar.vue'
__wxTemplateComponentProps['calendar'] && __wxTemplateComponentProps['calendar'].forEach(prop => __wxTemplateComponent0.props[prop] = {type: null})
import Header from './components/header/index.vue'
import Month from './components/month/index.vue'
import VanButton from '../button/index.vue'
import VanPopup from '../popup/index.vue'
import VanToast from '../toast/index.vue'
global['__wxVueOptions'] = {components:{'header': Header,'month': Month,'van-button': VanButton,'van-popup': VanPopup,'van-toast': VanToast,'calendar' : __wxTemplateComponent0}}

global['__wxRoute'] = 'vant/calendar/index'
import { VantComponent } from '../common/component';
import {
  ROW_HEIGHT,
  getNextDay,
  compareDay,
  copyDates,
  calcDateNum,
  formatMonthTitle,
  compareMonth,
  getMonths,
  getDayByOffset,
} from './utils';
import Toast from '../toast/toast';
import { requestAnimationFrame } from '../common/utils';
VantComponent({
  props: {
    title: {
      type: String,
      value: '日期选择',
    },
    color: String,
    show: {
      type: Boolean,
      observer(val) {
        if (val) {
          this.initRect();
          this.scrollIntoView();
        }
      },
    },
    formatter: null,
    confirmText: {
      type: String,
      value: '确定',
    },
    rangePrompt: String,
    defaultDate: {
      type: [Number, Array],
      observer(val) {
        this.setData({ currentDate: val });
        this.scrollIntoView();
      },
    },
    allowSameDay: Boolean,
    confirmDisabledText: String,
    type: {
      type: String,
      value: 'single',
      observer: 'reset',
    },
    minDate: {
      type: null,
      value: Date.now(),
    },
    maxDate: {
      type: null,
      value: new Date(
        new Date().getFullYear(),
        new Date().getMonth() + 6,
        new Date().getDate()
      ).getTime(),
    },
    position: {
      type: String,
      value: 'bottom',
    },
    rowHeight: {
      type: [Number, String],
      value: ROW_HEIGHT,
    },
    round: {
      type: Boolean,
      value: true,
    },
    poppable: {
      type: Boolean,
      value: true,
    },
    showMark: {
      type: Boolean,
      value: true,
    },
    showTitle: {
      type: Boolean,
      value: true,
    },
    showConfirm: {
      type: Boolean,
      value: true,
    },
    showSubtitle: {
      type: Boolean,
      value: true,
    },
    safeAreaInsetBottom: {
      type: Boolean,
      value: true,
    },
    closeOnClickOverlay: {
      type: Boolean,
      value: true,
    },
    maxRange: {
      type: [Number, String],
      value: null,
    },
  },
  data: {
    subtitle: '',
    currentDate: null,
    scrollIntoView: '',
  },
  created() {
    this.setData({
      currentDate: this.getInitialDate(),
    });
  },
  mounted() {
    if (this.data.show || !this.data.poppable) {
      this.initRect();
      this.scrollIntoView();
    }
  },
  methods: {
    reset() {
      this.setData({ currentDate: this.getInitialDate() });
      this.scrollIntoView();
    },
    initRect() {
      if (this.contentObserver != null) {
        this.contentObserver.disconnect();
      }
      const contentObserver = this.createIntersectionObserver({
        thresholds: [0, 0.1, 0.9, 1],
        observeAll: true,
      });
      this.contentObserver = contentObserver;
      contentObserver.relativeTo('.van-calendar__body');
      contentObserver.observe('.month', (res) => {
        if (res.boundingClientRect.top <= res.relativeRect.top) {
          // @ts-ignore
          this.setData({ subtitle: formatMonthTitle(res.dataset.date) });
        }
      });
    },
    getInitialDate() {
      const { type, defaultDate, minDate } = this.data;
      if (type === 'range') {
        const [startDay, endDay] = defaultDate || [];
        return [
          startDay || minDate,
          endDay || getNextDay(new Date(minDate)).getTime(),
        ];
      }
      if (type === 'multiple') {
        return defaultDate || [minDate];
      }
      return defaultDate || minDate;
    },
    scrollIntoView() {
      requestAnimationFrame(() => {
        const {
          currentDate,
          type,
          show,
          poppable,
          minDate,
          maxDate,
        } = this.data;
        const targetDate = type === 'single' ? currentDate : currentDate[0];
        const displayed = show || !poppable;
        if (!targetDate || !displayed) {
          return;
        }
        const months = getMonths(minDate, maxDate);
        months.some((month, index) => {
          if (compareMonth(month, targetDate) === 0) {
            this.setData({ scrollIntoView: `month${index}` });
            return true;
          }
          return false;
        });
      });
    },
    onOpen() {
      this.$emit('open');
    },
    onOpened() {
      this.$emit('opened');
    },
    onClose() {
      this.$emit('close');
    },
    onClosed() {
      this.$emit('closed');
    },
    onClickDay(event) {
      const { date } = event.detail;
      const { type, currentDate, allowSameDay } = this.data;
      if (type === 'range') {
        const [startDay, endDay] = currentDate;
        if (startDay && !endDay) {
          const compareToStart = compareDay(date, startDay);
          if (compareToStart === 1) {
            this.select([startDay, date], true);
          } else if (compareToStart === -1) {
            this.select([date, null]);
          } else if (allowSameDay) {
            this.select([date, date]);
          }
        } else {
          this.select([date, null]);
        }
      } else if (type === 'multiple') {
        let selectedIndex;
        const selected = currentDate.some((dateItem, index) => {
          const equal = compareDay(dateItem, date) === 0;
          if (equal) {
            selectedIndex = index;
          }
          return equal;
        });
        if (selected) {
          const cancelDate = currentDate.splice(selectedIndex, 1);
          this.setData({ currentDate });
          this.unselect(cancelDate);
        } else {
          this.select([...currentDate, date]);
        }
      } else {
        this.select(date, true);
      }
    },
    unselect(dateArray) {
      const date = dateArray[0];
      if (date) {
        this.$emit('unselect', copyDates(date));
      }
    },
    select(date, complete) {
      if (complete && this.data.type === 'range') {
        const valid = this.checkRange(date);
        if (!valid) {
          // auto selected to max range if showConfirm
          if (this.data.showConfirm) {
            this.emit([
              date[0],
              getDayByOffset(date[0], this.data.maxRange - 1),
            ]);
          } else {
            this.emit(date);
          }
          return;
        }
      }
      this.emit(date);
      if (complete && !this.data.showConfirm) {
        this.onConfirm();
      }
    },
    emit(date) {
      const getTime = (date) => (date instanceof Date ? date.getTime() : date);
      this.setData({
        currentDate: Array.isArray(date) ? date.map(getTime) : getTime(date),
      });
      this.$emit('select', copyDates(date));
    },
    checkRange(date) {
      const { maxRange, rangePrompt } = this.data;
      if (maxRange && calcDateNum(date) > maxRange) {
        Toast({
          context: this,
          message: rangePrompt || `选择天数不能超过 ${maxRange} 天`,
        });
        return false;
      }
      return true;
    },
    onConfirm() {
      if (
        this.data.type === 'range' &&
        !this.checkRange(this.data.currentDate)
      ) {
        return;
      }
      wx.nextTick(() => {
        this.$emit('confirm', copyDates(this.data.currentDate));
      });
    },
  },
});
export default global['__wxComponents']['vant/calendar/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-calendar{display:-webkit-flex;display:flex;-webkit-flex-direction:column;flex-direction:column;height:100%;height:var(--calendar-height,100%);background-color:#fff;background-color:var(--calendar-background-color,#fff)}.van-calendar__close-icon{top:11px}.van-calendar__popup--bottom,.van-calendar__popup--top{height:80%;height:var(--calendar-popup-height,80%)}.van-calendar__popup--left,.van-calendar__popup--right{height:100%}.van-calendar__body{-webkit-flex:1;flex:1;overflow:auto;-webkit-overflow-scrolling:touch}.van-calendar__footer{-webkit-flex-shrink:0;flex-shrink:0;padding:0 16px;padding:0 var(--padding-md,16px)}.van-calendar__footer--safe-area-inset-bottom{padding-bottom:env(safe-area-inset-bottom)}.van-calendar__footer+.van-calendar__footer,.van-calendar__footer:empty{display:none}.van-calendar__footer:empty+.van-calendar__footer{display:block!important}.van-calendar__confirm{height:36px!important;height:var(--calendar-confirm-button-height,36px)!important;margin:7px 0!important;margin:var(--calendar-confirm-button-margin,7px 0)!important;line-height:34px!important;line-height:var(--calendar-confirm-button-line-height,34px)!important}
</style>