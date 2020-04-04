/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByBookID", query = "SELECT b FROM Book b WHERE b.bookID = :bookID"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByBookDescription", query = "SELECT b FROM Book b WHERE b.bookDescription = :bookDescription"),
    @NamedQuery(name = "Book.findByIsbn", query = "SELECT b FROM Book b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Book.findByAuthorFirst", query = "SELECT b FROM Book b WHERE b.authorFirst = :authorFirst"),
    @NamedQuery(name = "Book.findByAuthorLast", query = "SELECT b FROM Book b WHERE b.authorLast = :authorLast"),
    @NamedQuery(name = "Book.findByPublisher", query = "SELECT b FROM Book b WHERE b.publisher = :publisher"),
    @NamedQuery(name = "Book.findByPubAddress", query = "SELECT b FROM Book b WHERE b.pubAddress = :pubAddress")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BookID")
    private Integer bookID;
    @Column(name = "Title")
    private String title;
    @Column(name = "BookDescription")
    private String bookDescription;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "AuthorFirst")
    private String authorFirst;
    @Column(name = "AuthorLast")
    private String authorLast;
    @Column(name = "Publisher")
    private String publisher;
    @Column(name = "PubAddress")
    private String pubAddress;
    @Lob
    @Column(name = "Cover")
    private byte[] cover;

    public Book() {
    }

    public Book(Integer bookID) {
        this.bookID = bookID;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorFirst() {
        return authorFirst;
    }

    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    public String getAuthorLast() {
        return authorLast;
    }

    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPubAddress() {
        return pubAddress;
    }

    public void setPubAddress(String pubAddress) {
        this.pubAddress = pubAddress;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookID != null ? bookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookID == null && other.bookID != null) || (this.bookID != null && !this.bookID.equals(other.bookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.Book[ bookID=" + bookID + " ]";
    }
    
}
