package me.lukeforit.spaceofaday.data.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity;

public class ApodMapper {

    @Inject
    public ApodMapper() {
    }

    public Apod map(ApodEntity entity) {
        return new Apod(
                entity.getCopyright(),
                entity.getDate(),
                entity.getExplanation(),
                entity.getHdurl(),
                entity.getMediaType(),
                entity.getServiceVersion(),
                entity.getTitle(),
                entity.getUrl());
    }

    public ApodEntity map(Apod apod) {
        return new ApodEntity(
                Utils.getDateAsInt(apod.getDate()),
                apod.getCopyright(),
                apod.getDate(),
                apod.getExplanation(),
                apod.getHdurl(),
                apod.getMediaType(),
                apod.getServiceVersion(),
                apod.getTitle(),
                apod.getUrl());
    }

    public List<Apod> map(List<ApodEntity> list) {
        ArrayList<Apod> result = new ArrayList<>();
        for (ApodEntity entity: list) {
            result.add(map(entity));
        }
        return result;
    }
}
