package com.synergy.bokja.service;

import com.synergy.bokja.dto.*;
import com.synergy.bokja.entity.*;
import com.synergy.bokja.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserMedicineRepository userMedicineRepository;
    private final CycleRepository cycleRepository;
    private final UserMedicineItemRepository userMedicineItemRepository;

    public ReportListResponseDTO getUserReports(Long umno) {
        List<ReportEntity> reports = reportRepository.findAllByUmno(umno);

        List<ReportItemDTO> reportList = reports.stream().map(report -> {
            CycleEntity cycle = cycleRepository.findByCyno(report.getCyno());
            UserMedicineEntity medicine = userMedicineRepository.findByUmno(report.getUmno());

            return new ReportItemDTO(
                    report.getRno(),
                    medicine.getHospital(),
                    medicine.getCategory(),
                    String.valueOf(cycle.getStart_date()),
                    String.valueOf(cycle.getEnd_date())
            );
        }).collect(Collectors.toList());

        return new ReportListResponseDTO(reportList);
    }

    public ReportDetailResponseDTO getReportDetail(Long rno) {
        ReportEntity report = reportRepository.findByRno(rno)
                .orElseThrow(() -> new IllegalArgumentException("리포트가 존재하지 않습니다."));

        UserMedicineEntity medicine = userMedicineRepository.findByUmno(report.getUmno());
        CycleEntity cycle = cycleRepository.findByCyno(report.getCyno());
        List<UserMedicineItemEntity> items = userMedicineItemRepository.findAllByUmino(report.getUmino());

        List<ColorDTO> colors = items.stream()
                .map(i -> new ColorDTO(
                        String.valueOf(cycle.getStart_date()), // 실제 복약 날짜 로직 추가 필요
                        i.getDescription() == null ? "g" : String.valueOf(i.getDescription())
                ))
                .collect(Collectors.toList());

        return new ReportDetailResponseDTO(
                report.getRno(),
                medicine.getHospital(),
                medicine.getCategory(),
                medicine.getTaken(),
                String.valueOf(cycle.getStart_date()),
                String.valueOf(cycle.getEnd_date()),
                colors
        );
    }

    public ReportSummaryResponseDTO getReportSummary(Long rno) {
        ReportEntity report = reportRepository.findByRno(rno)
                .orElseThrow(() -> new IllegalArgumentException("리포트가 존재하지 않습니다."));

        UserMedicineEntity medicine = userMedicineRepository.findByUmno(report.getUmno());
        CycleEntity cycle = cycleRepository.findByCyno(report.getCyno());
        List<UserMedicineItemEntity> items = userMedicineItemRepository.findAllByUmino(report.getUmino());

        List<ColorDTO> colors = items.stream()
                .map(i -> new ColorDTO(
                        String.valueOf(cycle.getStart_date()), // 실제 날짜 매핑 로직 필요
                        i.getDescription() == null ? "g" : String.valueOf(i.getDescription())
                ))
                .collect(Collectors.toList());

        return new ReportSummaryResponseDTO(
                report.getRno(),
                medicine.getHospital(),
                medicine.getCategory(),
                medicine.getTaken(),
                String.valueOf(cycle.getStart_date()),
                String.valueOf(cycle.getEnd_date()),
                colors
        );
    }
}
