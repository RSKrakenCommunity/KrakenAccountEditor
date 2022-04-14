package com.rshub.serialization

import com.rshub.models.AccountModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*

object AccountModelSerializer : KSerializer<AccountModel> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Account") {
        element<String>("email")
        element<String>("password")
        element<String>("group")
        element<Int>("world")
    }

    override fun serialize(encoder: Encoder, value: AccountModel) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.email.get())
            encodeStringElement(descriptor, 1, value.password.get())
            encodeStringElement(descriptor, 2, value.group.get())
            encodeIntElement(descriptor, 3, value.world.get())
        }
    }

    override fun deserialize(decoder: Decoder): AccountModel {
        return decoder.decodeStructure(descriptor) {
            var email = ""
            var password = ""
            var group = ""
            var world = 1
            while(true) {
                when(val index = decodeElementIndex(descriptor)) {
                    0 -> email = decodeStringElement(descriptor, index)
                    1 -> password = decodeStringElement(descriptor, index)
                    2 -> group = decodeStringElement(descriptor, index)
                    3 -> world = decodeIntElement(descriptor, index)
                    CompositeDecoder.DECODE_DONE -> break
                }
            }
            AccountModel(email, password, world, group)
        }
    }
}