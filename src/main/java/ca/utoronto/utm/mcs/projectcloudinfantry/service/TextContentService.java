package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.content.TextContent;
import org.springframework.stereotype.Service;


@Service
public interface TextContentService {
    TextContent getTextContent(Long oidTextPost);
    TextContent addTextContent(TextContent textPost);
}
