<template>
<uni-shadow-root class="vant-checkbox-group-index"><slot></slot></uni-shadow-root>
</template>

<script>

global['__wxRoute'] = 'vant/checkbox-group/index'
import { VantComponent } from '../common/component';
VantComponent({
  field: true,
  relation: {
    name: 'checkbox',
    type: 'descendant',
    current: 'checkbox-group',
    linked(target) {
      this.updateChild(target);
    },
  },
  props: {
    max: Number,
    value: {
      type: Array,
      observer: 'updateChildren',
    },
    disabled: {
      type: Boolean,
      observer: 'updateChildren',
    },
  },
  methods: {
    updateChildren() {
      (this.children || []).forEach((child) => this.updateChild(child));
    },
    updateChild(child) {
      const { value, disabled } = this.data;
      child.setData({
        value: value.indexOf(child.data.name) !== -1,
        parentDisabled: disabled,
      });
    },
  },
});
export default global['__wxComponents']['vant/checkbox-group/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';
</style>