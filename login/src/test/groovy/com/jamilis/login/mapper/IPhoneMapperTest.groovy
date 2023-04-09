package com.jamilis.login.mapper

import com.jamilis.login.dto.PhoneDto
import com.jamilis.login.entity.PhoneEntity
import spock.lang.Specification

class IPhoneMapperTest extends Specification {
    def "MapDto"() {
        given:
        def phoneDto = new PhoneDto(23456789, 11, "AR")
        when:
        def phoneEntity = IPhoneMapper.INSTANCE.mapDto(phoneDto)
        then:
        phoneEntity.getNumber().equals(phoneDto.getNumber())
        phoneEntity.cityCode.equals(phoneDto.getCityCode())
        phoneEntity.countryCode.equals(phoneDto.getCountryCode())
    }

    def "MapEntity"() {
        given:
        def phoneEntity = new PhoneEntity(1, UUID.randomUUID().toString(), 23456789, 11, "AR")
        when:
        def phoneDto = IPhoneMapper.INSTANCE.mapEntity(phoneEntity)
        then:
        phoneEntity.getNumber().equals(phoneDto.getNumber())
        phoneEntity.cityCode.equals(phoneDto.getCityCode())
        phoneEntity.countryCode.equals(phoneDto.getCountryCode())
    }

    def "MapDtoList"() {
        given:
        def phoneEntityList = [new PhoneEntity(1, UUID.randomUUID().toString(), 23456789, 11, "AR")]
        when:
        def phoneDtoList = IPhoneMapper.INSTANCE.mapEntityList(phoneEntityList)
        then:
        for(int i=0; i<phoneDtoList.size(); i++){
            phoneEntityList.get(i).getNumber().equals(phoneDtoList.get(i).getNumber())
            phoneEntityList.get(i).cityCode.equals(phoneDtoList.get(i).getCityCode())
            phoneEntityList.get(i).countryCode.equals(phoneDtoList.get(i).getCountryCode())
        }
    }

    def "MapEntityList"() {
        given:
        def phoneDtoList = [new PhoneDto(12345678, 11, "AR")]
        when:
        def phoneEntityList = IPhoneMapper.INSTANCE.mapDtoList(phoneDtoList)
        then:
        for(int i=0; i<phoneEntityList.size(); i++){
            phoneEntityList.get(i).getNumber().equals(phoneDtoList.get(i).getNumber())
            phoneEntityList.get(i).cityCode.equals(phoneDtoList.get(i).getCityCode())
            phoneEntityList.get(i).countryCode.equals(phoneDtoList.get(i).getCountryCode())
        }
    }
}
