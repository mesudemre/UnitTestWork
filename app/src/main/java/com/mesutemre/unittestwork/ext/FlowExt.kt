package com.mesutemre.unittestwork.ext

import com.mesutemre.unittestwork.core.BaseResourceEvent


fun <R, C> BaseResourceEvent<R>.convertRersourceEventType(
    convert: () -> C,
): BaseResourceEvent<C> {
    return if (this is BaseResourceEvent.Success) {
        BaseResourceEvent.Success(data = convert())
    } else if (this is BaseResourceEvent.Error) {
        BaseResourceEvent.Error(message = this.message, messageId = this.messageId)
    } else {
        BaseResourceEvent.Loading()
    }
}