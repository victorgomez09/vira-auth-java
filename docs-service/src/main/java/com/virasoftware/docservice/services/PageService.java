package com.virasoftware.docservice.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.virasoftware.common.exception.NotFoundException;
import com.virasoftware.common.exception.UnauthorizedException;
import com.virasoftware.docservice.domains.entities.Page;
import com.virasoftware.docservice.domains.entities.Space;
import com.virasoftware.docservice.domains.exceptions.PermissionsException;
import com.virasoftware.docservice.repositories.PageRepository;
import com.virasoftware.docservice.repositories.SpaceRepository;
import com.virasoftware.docservice.utils.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PageService {

    private PageRepository pageRepository;
    private SpaceRepository spaceRepository;

    public List<Page> findAllPagesBySpace(String spaceId, String userId) {
        Space space = spaceRepository.findByIdAndUser(spaceId, userId)
                .orElseThrow(() -> new UnauthorizedException("User not have permissions to see this space"));

        return pageRepository.findBySpace(space);
    }

    public Page findPageById(String pageId, String userId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new NotFoundException("Page not found"));

        // Check if use have space rights
        if (spaceRepository.findByIdAndUser(page.getSpace().getId(),
                userId).isEmpty()) {
            throw new PermissionsException("User not have permissions");
        }

        return page;
    }

    public Page createPage(Page requestData, String userId) {
        checkUserPermissions(requestData.getSpace().getId(), userId);

        Optional<Page> parentPage = pageRepository.findById(requestData.getParent().getId());
        Page page = new Page();
        page.setName(requestData.getName());
        page.setName(requestData.getBody());
        page.setParent(parentPage.isEmpty() ? null : parentPage.get());
        page.setOwner(userId);
        page.setTreePos(parentPage.isEmpty() ? Constants.INITIAL_TREE_POS : parentPage.get().getTreePos() + "10");
        page.setCreationDate(Instant.now());
        page.setModificationDate(Instant.now());

        return pageRepository.save(page);
    }

    public Page updatePage(Page requestData, String userId) {
        checkUserPermissions(requestData.getSpace().getId(), userId);

        Page page = pageRepository.findById(requestData.getId())
                .orElseThrow(() -> new NotFoundException("Page not found"));
        page.setName(requestData.getName());
        page.setName(requestData.getBody());
        page.setCreationDate(Instant.now());
        page.setModificationDate(Instant.now());

        return pageRepository.save(page);
    }

    public void deletePage(String pageId, String userId) {
        Page page = pageRepository.findById(pageId).orElseThrow(() -> new NotFoundException("Page not found"));

        checkUserPermissions(page.getSpace().getId(), userId);

        pageRepository.delete(page);
    }

    /**
     * PRIVATE METHODS
     */
    private void checkUserPermissions(String spaceId, String userId) {
        if (spaceRepository.findByIdAndUser(spaceId, userId).isEmpty()) {
            throw new PermissionsException("User have not see this space");
        }
    }

}
